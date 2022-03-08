package com.hahaen.wxshop.controller;

import com.hahaen.wxshop.entity.PageResponse;
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

    /**
     * @param pageNum
     * @param pageSize
     * @param shopId
     * @return 查询到的结果
     */
    @GetMapping("/goods")
    public @ResponseBody
    PageResponse<Goods> getGoods(@RequestParam("pageNum") Integer pageNum,
                                 @RequestParam("pageSize") Integer pageSize,
                                 @RequestParam(value = "shopId", required = false) Integer shopId) {
        return goodsService.getGoods(pageNum, pageSize, shopId);
    }

    /**
     * @param goods    goods to be created
     * @param response the HTTP response
     * @return the newly created goods
     */
    @PostMapping("/goods")
    public Response<Goods> createdGoods(@RequestBody Goods goods, HttpServletResponse response) {
        clean(goods);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return Response.of(goodsService.createGoods(goods));
    }

    private void clean(Goods goods) {
        goods.setId(null);
        goods.setCreatedAt(new Date());
        goods.setUpdatedAt(new Date());
    }

    /**
     * @param goods
     * @return 更新后的结果
     */
    public Response<Goods> updateGoods(Goods goods) {
        return Response.of(goodsService.updateGoods(goods));
    }

    /**
     * @param goodsId  the goods id to be deleted
     * @param response the HTTP response
     * @return the deleted goods
     */
    @DeleteMapping("/goods/{id}")
    public Response<Goods> deleteGoods(@PathVariable("id") Long goodsId, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        return Response.of(goodsService.deleteGoodsById(goodsId));
    }
}
