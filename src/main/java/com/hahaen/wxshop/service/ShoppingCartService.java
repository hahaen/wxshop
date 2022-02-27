package com.hahaen.wxshop.service;

import com.hahaen.wxshop.dao.ShoppingCartQueryMapper;
import com.hahaen.wxshop.entity.PageResponse;
import com.hahaen.wxshop.entity.ShoppingCartData;
import com.hahaen.wxshop.entity.ShoppingCartGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class ShoppingCartService {
    private ShoppingCartQueryMapper shoppingCartQueryMapper;

    @Autowired
    public ShoppingCartService(ShoppingCartQueryMapper shoppingCartQueryMapper) {
        this.shoppingCartQueryMapper = shoppingCartQueryMapper;
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
                .collect(toList());
        result.setGoods(goods);
        return result;
    }
}
