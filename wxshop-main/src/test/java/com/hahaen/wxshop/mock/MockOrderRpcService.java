package com.hahaen.wxshop.mock;

import com.hahaen.api.DataStatus;
import com.hahaen.api.data.OrderInfo;
import com.hahaen.api.data.PageResponse;
import com.hahaen.api.data.RpcOrderGoods;
import com.hahaen.api.generate.Order;
import com.hahaen.api.rpc.OrderRpcService;
import org.apache.dubbo.config.annotation.Service;
import org.mockito.Mock;

@Service(version = "${wxshop.orderservice.version}")
public class MockOrderRpcService implements OrderRpcService {
    @Mock
    public OrderRpcService orderRpcService;

    @Override
    public Order createOrder(OrderInfo orderInfo, Order order) {
        return orderRpcService.createOrder(orderInfo, order);
    }

    @Override
    public RpcOrderGoods getOrderById(long orderId) {
        return orderRpcService.getOrderById(orderId);
    }

    @Override
    public RpcOrderGoods deleteOrder(long orderId, long userId) {
        return orderRpcService.deleteOrder(orderId, userId);
    }

    @Override
    public PageResponse<RpcOrderGoods> getOrder(long userId, Integer pageNum, Integer pageSize, DataStatus status) {
        return orderRpcService.getOrder(userId, pageNum, pageSize, status);
    }

    @Override
    public RpcOrderGoods updateOrder(Order order) {
        return orderRpcService.updateOrder(order);
    }
}
