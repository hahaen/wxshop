package com.hahaen.wxshop.controller;

import com.hahaen.api.DataStatus;
import com.hahaen.api.data.OrderInfo;
import com.hahaen.api.exceptions.HttpException;
import com.hahaen.wxshop.entity.OrderResponse;
import com.hahaen.api.data.PageResponse;
import com.hahaen.wxshop.entity.Response;
import com.hahaen.wxshop.service.OrderService;
import com.hahaen.wxshop.service.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public PageResponse<OrderResponse> getOrder(@RequestParam("pageNum") Integer pageNum,
                                                @RequestParam("pageSize") Integer pageSize,
                                                @RequestParam(value = "status", required = false) String status) {
        if (status != null && DataStatus.fromStatus(status) == null) {
            throw HttpException.badRequest("非法status: " + status);
        }
        return orderService.getOrder(pageNum, pageSize, DataStatus.fromStatus(status));
    }

    /**
     * @param orderInfo
     * @return 响应
     */
    @PostMapping("/order")
    public Response<OrderResponse> createOrder(@RequestBody OrderInfo orderInfo) {
        orderService.deductStock(orderInfo);
        return Response.of(orderService.createOrder(orderInfo, UserContext.getCurrentUser().getId()));
    }

    public void updateOrder() {
    }

    @DeleteMapping("/order/{id}")
    public Response<OrderResponse> deleteOrder(@PathVariable("id") long orderId) {
        return Response.of(orderService.deleteOrder(orderId, UserContext.getCurrentUser().getId()));
    }
}
