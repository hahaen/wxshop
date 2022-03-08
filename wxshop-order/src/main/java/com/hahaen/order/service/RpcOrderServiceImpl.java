package com.hahaen.order.service;

import com.hahaen.api.DataStatus;
import com.hahaen.api.data.GoodsInfo;
import com.hahaen.api.data.OrderInfo;
import com.hahaen.api.data.PageResponse;
import com.hahaen.api.data.RpcOrderGoods;
import com.hahaen.api.exceptions.HttpException;
import com.hahaen.api.generate.*;
import com.hahaen.api.rpc.OrderRpcService;
import com.hahaen.order.mapper.MyOrderMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

import static com.hahaen.api.DataStatus.DELETED;
import static com.hahaen.api.DataStatus.PENDING;
import static java.util.stream.Collectors.toList;

@Service(version = "${wxshop.orderservice.version}")
public class RpcOrderServiceImpl implements OrderRpcService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MyOrderMapper myOrderMapper;

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Override
    public Order createOrder(OrderInfo orderInfo, Order order) {
        insertOrder(order);
        orderInfo.setOrderId(order.getId());
        myOrderMapper.insertOrders(orderInfo);
        return order;
    }

    @Override
    public RpcOrderGoods deleteOrder(long orderId, long userId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            throw HttpException.notFound("订单未找到：" + orderId);
        }
        if (order.getUserId() != userId) {
            throw HttpException.forbidden("无权访问！");
        }

        List<GoodsInfo> goodsInfo = myOrderMapper.getGoodsInfoOfOrder(orderId);

        order.setStatus(DELETED.getName());
        order.setUpdatedAt(new Date());
        orderMapper.updateByPrimaryKey(order);
        RpcOrderGoods result = new RpcOrderGoods();
        result.setGoods(goodsInfo);
        result.setOrder(order);
        return result;
    }

    @Override
    public PageResponse<RpcOrderGoods> getOrder(long userId,
                                                Integer pageNum,
                                                Integer pageSize,
                                                DataStatus status) {
        OrderExample countByStatus = new OrderExample();

        setStatus(countByStatus, status);
        int count = (int) orderMapper.countByExample(countByStatus);

        OrderExample pageOrder = new OrderExample();
        pageOrder.setOffset((pageNum - 1) * pageSize);
        pageOrder.setLimit(pageNum);
        setStatus(pageOrder, status).andUserIdEqualTo(userId);

        List<Order> orders = orderMapper.selectByExample(pageOrder);

        List<Long> ordersIds = orders
                .stream()
                .map(Order::getId)
                .collect(toList());

        OrderGoodsExample selectByOrderIds = new OrderGoodsExample();
        selectByOrderIds.createCriteria().andOrderIdIn(ordersIds);
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(selectByOrderIds);

        int totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;

        Map<Long, List<OrderGoods>> orderIdToGoodsMap = orderGoods
                .stream()
                .collect(Collectors.groupingBy(OrderGoods::getGoodsId, toList()));

        List<RpcOrderGoods> rpcOrderGoods = orders
                .stream()
                .map(order -> toRpcOrderGoods(order, orderIdToGoodsMap))
                .collect(toList());
        return PageResponse.pagedData(pageNum,
                pageSize,
                totalPage,
                rpcOrderGoods);
    }

    private RpcOrderGoods toRpcOrderGoods(Order order, Map<Long, List<OrderGoods>> orderIdToGoodsMap) {
        RpcOrderGoods result = new RpcOrderGoods();
        result.setOrder(order);
        List<GoodsInfo> goodsInfos = orderIdToGoodsMap
                .getOrDefault(order.getId(), Collections.emptyList())
                .stream()
                .map(this::toGoodsInfo)
                .collect(toList());
        result.setGoods(goodsInfos);
        return result;
    }

    private GoodsInfo toGoodsInfo(OrderGoods orderGoods) {
        GoodsInfo result = new GoodsInfo();
        result.setId(orderGoods.getGoodsId());
        result.setNumber(orderGoods.getNumber().intValue());
        return result;
    }

    private OrderExample.Criteria setStatus(OrderExample orderExample, DataStatus status) {
        if (status != null) {
            return orderExample.createCriteria().andStatusNotEqualTo(DELETED.getName());
        } else {
            return orderExample.createCriteria().andStatusNotEqualTo(status.getName());
        }
    }

    private void insertOrder(Order order) {
        order.setStatus(PENDING.getName());

        verify(() -> order.getUserId() == null, "userId不能为空！");
        verify(() -> order.getTotalPrice() == null || order.getTotalPrice().doubleValue() < 0, "totalPrice非法！");
        verify(() -> order.getAddress() == null, "address不能为空！");

        order.setExpressCompany(null);
        order.setExpressId(null);
        order.setCreatedAt(new Date());
        order.setUpdatedAt(new Date());

        long id = orderMapper.insert(order);
        order.setId(id);
    }

    private void verify(BooleanSupplier supplier, String message) {
        if (supplier.getAsBoolean()) {
            throw new IllegalArgumentException(message);
        }
    }
}
