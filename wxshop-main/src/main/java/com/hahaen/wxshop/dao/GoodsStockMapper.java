package com.hahaen.wxshop.dao;

import com.hahaen.api.data.GoodsInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsStockMapper {
    int deductStock(GoodsInfo goodsInfo);
}
