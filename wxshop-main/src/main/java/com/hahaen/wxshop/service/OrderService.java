package com.hahaen.wxshop.service;

import com.hahaen.api.DataStatus;
import com.hahaen.api.data.GoodsInfo;
import com.hahaen.api.data.OrderInfo;
import com.hahaen.api.data.RpcOrderGoods;
import com.hahaen.api.generate.Order;
import com.hahaen.api.rpc.OrderRpcService;
import com.hahaen.wxshop.dao.GoodsStockMapper;
import com.hahaen.wxshop.entity.GoodsWithNumber;
import com.hahaen.api.exceptions.HttpException;
import com.hahaen.wxshop.entity.OrderResponse;
import com.hahaen.api.data.PageResponse;
import com.hahaen.wxshop.generate.Goods;
import com.hahaen.wxshop.generate.ShopMapper;
import com.hahaen.wxshop.generate.UserMapper;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Service
public class OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    @Reference(version = "${wxshop.orderservice.version}")
    private OrderRpcService orderRpcService;

    private UserMapper userMapper;

    private GoodsStockMapper goodsStockMapper;

    private GoodsService goodsService;

    private ShopMapper shopMapper;

    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    public OrderService(UserMapper userMapper,
                        GoodsStockMapper goodsStockMapper,
                        ShopMapper shopMapper,
                        GoodsService goodsService,
                        SqlSessionFactory sqlSessionFactory) {
        this.userMapper = userMapper;
        this.shopMapper = shopMapper;
        this.goodsService = goodsService;
        this.goodsStockMapper = goodsStockMapper;
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public OrderResponse createOrder(OrderInfo orderInfo, Long userId) {
        Map<Long, Goods> idToGoodsMap = getIdToGoodsMap(orderInfo.getGoods());
        Order createdOrder = createOrderViaRpc(orderInfo, userId, idToGoodsMap);
        return generateResponse(createdOrder, idToGoodsMap, orderInfo.getGoods());
    }

    private OrderResponse generateResponse(Order createdOrder, Map<Long, Goods> idToGoodsMap, List<GoodsInfo> goodsInfo) {
        OrderResponse response = new OrderResponse(createdOrder);

        Long shopId = new ArrayList<>(idToGoodsMap.values()).get(0).getShopId();
        response.setShop(shopMapper.selectByPrimaryKey(shopId));
        response.setGoods(
                goodsInfo
                        .stream()
                        .map(goods -> toGoodsWithNumber(goods, idToGoodsMap))
                        .collect(toList())
        );

        return response;
    }

    private Map<Long, Goods> getIdToGoodsMap(List<GoodsInfo> goodsInfos) {
        List<Long> goodsId = goodsInfos
                .stream()
                .map(GoodsInfo::getId)
                .collect(toList());
        return goodsService.getIdToGoodsMap(goodsId);
    }

    private Order createOrderViaRpc(OrderInfo orderInfo, Long userId, Map<Long, Goods> idToGoodsMap) {
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(DataStatus.PENDING.getName());
        order.setAddress(userMapper.selectByPrimaryKey(userId).getAddress());
        order.setTotalPrice(calculateTotalPrice(orderInfo, idToGoodsMap));

        return orderRpcService.createOrder(orderInfo, order);
    }

    @Transactional
    public void deductStock(OrderInfo orderInfo) {
        for (GoodsInfo goodsInfo : orderInfo.getGoods()) {
            if (goodsStockMapper.deductStock(goodsInfo) <= 0) {
                LOGGER.error("扣减库存失败, 商品id: " + goodsInfo.getId() + "，数量：" + goodsInfo.getNumber());
                throw HttpException.gone("扣减库存失败！");
            }
        }
    }

    private GoodsWithNumber toGoodsWithNumber(GoodsInfo goodsInfo, Map<Long, Goods> idToGoodsMap) {
        GoodsWithNumber ret = new GoodsWithNumber(idToGoodsMap.get(goodsInfo.getId()));
        ret.setNumber(goodsInfo.getNumber());
        return ret;
    }

    private long calculateTotalPrice(OrderInfo orderInfo, Map<Long, Goods> idToGoodsMap) {
        long result = 0;

        for (GoodsInfo goodsInfo : orderInfo.getGoods()) {
            Goods goods = idToGoodsMap.get(goodsInfo.getId());
            if (goods == null) {
                throw HttpException.badRequest("goods id非法：" + goodsInfo.getId());
            }
            if (goodsInfo.getNumber() <= 0) {
                throw HttpException.badRequest("number非法：" + goodsInfo.getNumber());
            }

            result = result + goods.getPrice() * goodsInfo.getNumber();
        }

        return result;
    }


    public OrderResponse deleteOrder(long orderId, long userId) {
        RpcOrderGoods rpcOrderGoods = orderRpcService.deleteOrder(orderId, userId);
        Map<Long, Goods> idToGoodsMap = getIdToGoodsMap(rpcOrderGoods.getGoods());
        return generateResponse(rpcOrderGoods.getOrder(), idToGoodsMap, rpcOrderGoods.getGoods());
    }

    public PageResponse<OrderResponse> getOrder(long userId,
                                                Integer pageNum,
                                                Integer pageSize,
                                                DataStatus status) {
        PageResponse<RpcOrderGoods> rpcOrderGoods = orderRpcService.getOrder(userId, pageNum, pageSize, status);
        List<GoodsInfo> goodIds = rpcOrderGoods
                .getData()
                .stream()
                .map(RpcOrderGoods::getGoods)
                .flatMap(List::stream)
                .collect(toList());

        Map<Long, Goods> idToGoodsMap = getIdToGoodsMap(goodIds);

        List<OrderResponse> orders = rpcOrderGoods.getData()
                .stream()
                .map(order -> generateResponse(order.getOrder(), idToGoodsMap, order.getGoods()))
                .collect(toList());

        return PageResponse.pagedData(
                rpcOrderGoods.getPageNum(),
                rpcOrderGoods.getPageSize(),
                rpcOrderGoods.getTotalPage(),
                orders
        );
    }
}
