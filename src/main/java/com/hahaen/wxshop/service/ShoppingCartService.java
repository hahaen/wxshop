package com.hahaen.wxshop.service;

import com.hahaen.wxshop.controller.ShoppingCartController;
import com.hahaen.wxshop.dao.ShoppingCartQueryMapper;
import com.hahaen.wxshop.entity.*;
import com.hahaen.wxshop.generate.*;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
public class ShoppingCartService {
    private ShoppingCartQueryMapper shoppingCartQueryMapper;
    private GoodsMapper goodsMapper;
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    public ShoppingCartService(ShoppingCartQueryMapper shoppingCartQueryMapper, GoodsMapper goodsMapper, SqlSessionFactory sqlSessionFactory) {
        this.shoppingCartQueryMapper = shoppingCartQueryMapper;
        this.goodsMapper = goodsMapper;
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public PageResponse<ShoppingCartData> getShoppingCartOfUser(Long userId,
                                                                int pageNum,
                                                                int pageSize) {
        int offect = (pageNum - 1) * pageSize;

        int totalNum = shoppingCartQueryMapper.countHowManyShopsInUserShoppingCart(userId);
        List<ShoppingCartData> pageData =
                shoppingCartQueryMapper.selectShoppingCartDataByUserId(userId, pageSize, offect)
                        .stream()
                        .collect(groupingBy(shoppingCartData -> shoppingCartData.getShop().getId()))
                        .values()
                        .stream().map(this::merge)
                        .collect(Collectors.toList());


        int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        return PageResponse.pagedData(pageNum, pageSize, totalPage, pageData);
    }

    private ShoppingCartData merge(List<ShoppingCartData> goodsOfSameShop) {
        ShoppingCartData result = new ShoppingCartData();
        result.setShop(goodsOfSameShop.get(0).getShop());
        List<ShoppingCartGoods> goods = goodsOfSameShop.stream()
                .map(ShoppingCartData::getGoods)
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .collect(toList());
        result.setGoods(goods);
        return result;
    }

    public ShoppingCartData addToShoppingCart(ShoppingCartController.AddToShoppingCartRequest request) {
        List<Long> goodsId = request.getGoods()
                .stream()
                .map(ShoppingCartController.AddToShoppingCartItem::getId)
                .collect(toList());

        if (goodsId.isEmpty()) {
            throw HttpException.badRequest("商品ID为空!");
        }

        GoodsExample example = new GoodsExample();
        example.createCriteria().andIdIn(goodsId);
        List<Goods> goods = goodsMapper.selectByExample(example);

        if (goods.stream().map(Goods::getShopId).collect(toSet()).size() != 1) {
            throw HttpException.notFound("商品ID非法!");
        }

        Map<Long, Goods> idToGoodsMap = goods.stream().collect(toMap(Goods::getId, x -> x));

        List<ShoppingCart> shoppingCartRows = request.getGoods()
                .stream()
                .map(item -> toShoppingCartRow(item, idToGoodsMap))
                .filter(Objects::nonNull)
                .collect(toList());

        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);
            shoppingCartRows.forEach(mapper::insert);
            sqlSession.commit();
        }

        return merge(shoppingCartQueryMapper.selectShoppingCartDataByUserIdShopId(
                UserContext.getCurrentUser().getId(),
                goods.get(0).getShopId()
        ));
    }

    private ShoppingCart toShoppingCartRow(ShoppingCartController.AddToShoppingCartItem item,
                                           Map<Long, Goods> idToGoodsMap) {

        Goods goods = idToGoodsMap.get(item.getId());
        if (goods == null) {
            return null;
        }

        ShoppingCart result = new ShoppingCart();
        result.setGoodsId(item.getId());
        result.setNumber(item.getNumber());
        result.setUserId(UserContext.getCurrentUser().getId());
        result.setShopId(goods.getShopId());
        result.setStatus(DataStatus.OK.toString().toLowerCase());
        result.setCreatedAt(new Date());
        result.setUpdatedAt(new Date());
        return result;
    }
}
