package com.hahaen.wxshop.controller;

import com.hahaen.api.DataStatus;
import com.hahaen.api.data.OrderInfo;
import com.hahaen.api.data.PageResponse;
import com.hahaen.api.exceptions.HttpException;
import com.hahaen.api.generate.Order;
import com.hahaen.wxshop.entity.OrderResponse;
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
        return orderService.getOrder(UserContext.getCurrentUser().getId(), pageNum, pageSize, DataStatus.fromStatus(status));
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

    @RequestMapping(value = "/order/{id}", method = {RequestMethod.POST, RequestMethod.PATCH})
    public Response<OrderResponse> updateOrder(@PathVariable("id") Integer id, @RequestBody Order order) {
        if (order.getExpressCompany() != null) {
            return Response.of(orderService.updateExpressInformation(order, UserContext.getCurrentUser().getId()));
        } else {
            return Response.of(orderService.updateOrderStatus(order, UserContext.getCurrentUser().getId()));
        }
    }

    @DeleteMapping("/order/{id}")
    public Response<OrderResponse> deleteOrder(@PathVariable("id") long orderId) {
        return Response.of(orderService.deleteOrder(orderId, UserContext.getCurrentUser().getId()));
    }
}
