package com.hahaen.wxshop.controller;

import com.hahaen.wxshop.dao.GoodsDao;
import com.hahaen.wxshop.entity.Response;
import com.hahaen.wxshop.generate.Goods;
import com.hahaen.wxshop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class GoodsController {
    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    public void getGoods() {
    }


    @PostMapping("/goods")
    public Response<Goods> createdGoods(@RequestBody Goods goods, HttpServletResponse response) {
        clean(goods);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            return Response.of(goodsService.createGoods(goods));
        } catch (GoodsService.NotAuthorizedForShopException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return Response.of(e.getMessage(), null);
        }
    }

    private void clean(Goods goods) {
        goods.setId(null);
        goods.setCreatedAt(new Date());
        goods.setUpdatedAt(new Date());
    }


    public void updateGoods() {
    }

    @DeleteMapping("goods/{id}")
    public Response<Goods> deleteGoods(@PathVariable("id") Long goodsId, HttpServletResponse response) {
        try {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return Response.of(goodsService.deleteGoodsById(goodsId));
        } catch (GoodsService.NotAuthorizedForShopException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return Response.of(e.getMessage(), null);
        } catch (GoodsDao.ResourceNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return Response.of(e.getMessage(), null);
        }
    }
}
