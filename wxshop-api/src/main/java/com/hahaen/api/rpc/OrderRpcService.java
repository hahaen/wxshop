package com.hahaen.api.rpc;

import com.hahaen.api.data.OrderInfo;
import com.hahaen.api.generate.Order;

public interface OrderRpcService {
    Order createOrder(OrderInfo orderInfo, Order order);
}
