package com.hahaen.order.mapper;

import com.hahaen.api.data.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyOrderMapper {
    void insertOrders(OrderInfo orderInfo);
}
