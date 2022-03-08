package com.hahaen.wxshop.controller;

import com.hahaen.wxshop.entity.HttpException;
import com.hahaen.wxshop.entity.PageResponse;
import com.hahaen.wxshop.entity.Response;
import com.hahaen.wxshop.generate.Shop;
import com.hahaen.wxshop.service.ShopService;
import com.hahaen.wxshop.service.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    // @formatter:on
    @GetMapping("/shop")
    public PageResponse<Shop> getShop(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return shopService.getShopByUserId(UserContext.getCurrentUser().getId(), pageNum, pageSize);
    }

    /**
     * @param shop
     * @param response
     * @return 新创建的店铺
     */
    // @formatter:on
    @PostMapping("/shop")
    public Response<Shop> createShop(@RequestBody Shop shop, HttpServletResponse response) {
        Response<Shop> ret = Response.of(shopService.createShop(shop, UserContext.getCurrentUser().getId()));
        response.setStatus(HttpStatus.CREATED.value());
        return ret;
    }

    /**
     * 更新店铺的信息
     *
     * @param id
     * @param shop
     * @param response
     * @return 更新后的店铺
     */
    // @formatter:on
    @PatchMapping("/shop/{id}")
    public Response<Shop> updateShop(@PathVariable("id") Long id,
                                     @RequestBody Shop shop,
                                     HttpServletResponse response) {
        shop.setId(id);
        try {
            return Response.of(shopService.updateShop(shop, UserContext.getCurrentUser().getId()));
        } catch (HttpException e) {
            response.setStatus(e.getStatusCode());
            return Response.of(e.getMessage(), null);
        }
    }

    /**
     * 删除店铺
     *
     * @param shopId
     * @param response
     * @return 刚刚删除的店铺
     */
    @DeleteMapping("/shop/{id}")
    public Response<Shop> deleteShop(@PathVariable("id") Long shopId, HttpServletResponse response) {
        try {
            return Response.of(shopService.deleteShop(shopId, UserContext.getCurrentUser().getId()));
        } catch (HttpException e) {
            response.setStatus(e.getStatusCode());
            return Response.of(e.getMessage(), null);
        }
    }
}
