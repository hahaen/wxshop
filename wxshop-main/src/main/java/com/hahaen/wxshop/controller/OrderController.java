package com.hahaen.wxshop.controller;

import com.hahaen.api.rpc.OrderService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    @Reference(version = "${wxshop.orderservice.version}")
    OrderService orderService;

    public void getOrder() {
    }


    public void createOrder() {
    }


    public void updateOrder() {
    }


    public void deleteOrder() {
    }
}