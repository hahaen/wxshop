package com.hahaen.wxshop.controller;

import com.hahaen.api.data.OrderInfo;
import com.hahaen.wxshop.entity.HttpException;
import com.hahaen.wxshop.entity.OrderResponse;
import com.hahaen.wxshop.entity.Response;
import com.hahaen.wxshop.service.OrderService;
import com.hahaen.wxshop.service.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void getOrder() {
    }

    /**
     * @param orderInfo
     * @param response
     * @return 响应
     */
    @PostMapping("/order")
    public Response<OrderResponse> createOrder(@RequestBody OrderInfo orderInfo, HttpServletResponse response) {
        try {
            orderService.deductStock(orderInfo);
            return Response.of(orderService.createOrder(orderInfo, UserContext.getCurrentUser().getId()));
        } catch (HttpException e) {
            response.setStatus(e.getStatusCode());
            return Response.of(e.getMessage(), null);
        }
    }

    public void updateOrder() {
    }

    public void deleteOrder() {
    }
}
