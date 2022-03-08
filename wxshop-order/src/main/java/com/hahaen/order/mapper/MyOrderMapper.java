package com.hahaen.order.mapper;

import com.hahaen.api.data.GoodsInfo;
import com.hahaen.api.data.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyOrderMapper {
    void insertOrders(OrderInfo orderInfo);

    List<GoodsInfo> getGoodsInfoOfOrder(long orderId);
}
