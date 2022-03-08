package com.hahaen.wxshop.controller;

import com.hahaen.api.data.PageResponse;
import com.hahaen.wxshop.entity.Response;
import com.hahaen.wxshop.generate.Shop;
import com.hahaen.wxshop.service.ShopService;
import com.hahaen.wxshop.service.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1")
public class ShopController {
    private ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    /**
     * @param pageNum
     * @param pageSize
     * @return 店铺列表
     */
    @GetMapping("/shop")
    public PageResponse<Shop> getShop(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return shopService.getShopByUserId(UserContext.getCurrentUser().getId(), pageNum, pageSize);
    }

    /**
     * @param shop
     * @param response
     * @return 店铺
     */
    @PostMapping("/shop")
    public Response<Shop> createShop(@RequestBody Shop shop, HttpServletResponse response) {
        Response<Shop> ret = Response.of(shopService.createShop(shop, UserContext.getCurrentUser().getId()));
        response.setStatus(HttpStatus.CREATED.value());
        return ret;
    }

    /**
     * @param id
     * @param shop
     * @return 更新后的店铺
     */
    @PatchMapping("/shop/{id}")
    public Response<Shop> updateShop(@PathVariable("id") Long id,
                                     @RequestBody Shop shop) {
        shop.setId(id);
        return Response.of(shopService.updateShop(shop, UserContext.getCurrentUser().getId()));
    }

    /**
     * @param shopId
     * @return 刚刚删除的店铺
     */
    @DeleteMapping("/shop/{id}")
    public Response<Shop> deleteShop(@PathVariable("id") Long shopId) {
        return Response.of(shopService.deleteShop(shopId, UserContext.getCurrentUser().getId()));
    }
}
