package com.hahaen.wxshop.service;

import com.hahaen.wxshop.entity.DataStatus;
import com.hahaen.wxshop.entity.HttpException;
import com.hahaen.wxshop.entity.PageResponse;
import com.hahaen.wxshop.generate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GoodsService {
    private GoodsMapper goodsMapper;
    private ShopMapper shopMapper;

    @Autowired
    public GoodsService(GoodsMapper goodsMapper, ShopMapper shopMapper) {
        this.goodsMapper = goodsMapper;
        this.shopMapper = shopMapper;
    }

    public Goods createGoods(Goods goods) {
        Shop shop = shopMapper.selectByPrimaryKey(goods.getShopId());

        if (shop == null || Objects.equals(shop.getOwnerUserId(), UserContext.getCurrentUser().getId())) {
            goods.setStatus(DataStatus.OK.getName());
            long id = goodsMapper.insert(goods);
            goods.setId(id);
            return goods;
        } else {
            throw HttpException.forbidden("无权访问！");
        }
    }

    public Goods updateGoods(Goods goods) {
        Shop shop = shopMapper.selectByPrimaryKey(goods.getShopId());

        if (Objects.equals(shop.getOwnerUserId(), UserContext.getCurrentUser().getId())) {
            GoodsExample byId = new GoodsExample();
            byId.createCriteria().andIdEqualTo(goods.getId());
            int affectedRows = goodsMapper.updateByExample(goods, byId);
            if (affectedRows == 0) {
                throw HttpException.notFound("未找到");
            }
            return goods;
        } else {
            throw HttpException.forbidden("无权访问！");
        }
    }

    public Goods deleteGoodsById(Long goodsId) {
        Shop shop = shopMapper.selectByPrimaryKey(goodsId);

        if (shop == null || Objects.equals(shop.getOwnerUserId(), UserContext.getCurrentUser().getId())) {
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            if (goods == null) {
                throw HttpException.notFound("商品未找到！");
            }

            goods.setStatus(DataStatus.DELETED.getName());
            goodsMapper.updateByPrimaryKey(goods);
            return goods;
        } else {
            throw HttpException.forbidden("无权访问！");
        }
    }

    public PageResponse<Goods> getGoods(Integer pageNum, Integer pageSize, Integer shopId) {
        int totalNumber = countGoods(shopId);
        int totalPage = totalNumber % pageSize == 0 ? totalNumber / pageSize : totalNumber / pageSize + 1;

        GoodsExample page = new GoodsExample();
        page.setLimit(pageSize);
        page.setOffset((pageNum - 1) * pageSize);

        List<Goods> pagedGoods = goodsMapper.selectByExample(page);
        return PageResponse.pagedData(pageNum, pageSize, totalPage, pagedGoods);
    }

    private int countGoods(Integer shopId) {
        if (shopId == null) {
            GoodsExample goodsExample = new GoodsExample();
            goodsExample.createCriteria().andStatusEqualTo(DataStatus.OK.getName());
            return (int) goodsMapper.countByExample(goodsExample);
        } else {
            GoodsExample goodsExample = new GoodsExample();
            goodsExample.createCriteria()
                    .andStatusEqualTo(DataStatus.OK.getName())
                    .andShopIdEqualTo(shopId.longValue());

            return (int) goodsMapper.countByExample(goodsExample);
        }
    }
}
