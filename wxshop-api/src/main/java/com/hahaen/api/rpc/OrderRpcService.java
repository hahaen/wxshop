package com.hahaen.api.rpc;

import com.hahaen.api.DataStatus;
import com.hahaen.api.data.OrderInfo;
import com.hahaen.api.data.PageResponse;
import com.hahaen.api.data.RpcOrderGoods;
import com.hahaen.api.generate.Order;

public interface OrderRpcService {
    Order createOrder(OrderInfo orderInfo, Order order);

    RpcOrderGoods deleteOrder(long orderId, long userId);

    PageResponse<RpcOrderGoods> getOrder(long userId, Integer pageNum, Integer pageSize, DataStatus status);

}
