package com.hahaen.wxshop.controller;

import com.hahaen.api.data.PageResponse;
import com.hahaen.wxshop.entity.Response;
import com.hahaen.wxshop.entity.ShoppingCartData;
import com.hahaen.wxshop.service.ShoppingCartService;
import com.hahaen.wxshop.service.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ShoppingCartController {
    private static Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    /**
     * @param pageNum  页码
     * @param pageSize 每页元素数量
     * @return 结果
     */
    // @formatter:on
    @GetMapping("/shoppingCart")
    public PageResponse<ShoppingCartData> getShoppingCart(
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize
    ) {
        return shoppingCartService.getShoppingCartOfUser(UserContext.getCurrentUser().getId(),
                pageNum,
                pageSize);
    }

    /**
     * @param request 加购物车请求
     * @return 添加后的结果
     */
    @PostMapping("/shoppingCart")
    public Response<ShoppingCartData> addToShoppingCart(@RequestBody AddToShoppingCartRequest request) {
        return Response.of(shoppingCartService.addToShoppingCart(request, UserContext.getCurrentUser().getId()));
    }


    public static class AddToShoppingCartRequest {
        List<AddToShoppingCartItem> goods;

        public List<AddToShoppingCartItem> getGoods() {
            return goods;
        }

        public void setGoods(List<AddToShoppingCartItem> goods) {
            this.goods = goods;
        }
    }

    public static class AddToShoppingCartItem {
        long id;
        int number;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

    /**
     * @param goodsId 要删除的商品id
     * @return 更新后的该店铺数据
     */
    @DeleteMapping("/shoppingCart/{id}")
    public Response<ShoppingCartData> deleteGoodsInShoppingCart(@PathVariable("id") Long goodsId) {
        return Response.of(shoppingCartService.deleteGoodsInShoppingCart(goodsId, UserContext.getCurrentUser().getId()));
    }
}
