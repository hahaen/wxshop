package com.hahaen.wxshop.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hahaen.api.DataStatus;
import com.hahaen.api.data.GoodsInfo;
import com.hahaen.api.data.OrderInfo;
import com.hahaen.api.data.PageResponse;
import com.hahaen.api.data.RpcOrderGoods;
import com.hahaen.api.generate.Order;
import com.hahaen.wxshop.WxshopApplication;
import com.hahaen.wxshop.entity.GoodsWithNumber;
import com.hahaen.wxshop.entity.OrderResponse;
import com.hahaen.wxshop.entity.Response;
import com.hahaen.wxshop.generate.Goods;
import com.hahaen.wxshop.generate.Shop;
import com.hahaen.wxshop.mock.MockOrderRpcService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WxshopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.config.location=classpath:test-application.yml"})
public class OrderIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    MockOrderRpcService mockOrderRpcService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(mockOrderRpcService);
    }

    @Test
    public void canCreateOrder() throws Exception {
        UserLoginResponse loginResponse = loginAndGetCookie();

        OrderInfo orderInfo = new OrderInfo();
        GoodsInfo goodsInfo1 = new GoodsInfo();
        GoodsInfo goodsInfo2 = new GoodsInfo();

        goodsInfo1.setId(4);
        goodsInfo1.setNumber(3);
        goodsInfo2.setId(5);
        goodsInfo2.setNumber(5);

        orderInfo.setGoods(Arrays.asList(goodsInfo1, goodsInfo2));

        when(mockOrderRpcService.orderRpcService.createOrder(any(), any())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Order order = invocation.getArgument(1);
                order.setId(1234L);
                return order;
            }
        });

        Response<OrderResponse> response = doHttpRequest("/api/v1/order", "POST", orderInfo, loginResponse.cookie)
                .assertOkStatusCode()
                .asJsonObject(new TypeReference<Response<OrderResponse>>() {
                });

        Assertions.assertEquals(1234L, response.getData().getId());

        Assertions.assertEquals(2L, response.getData().getShop().getId());
        Assertions.assertEquals(2L, response.getData().getShopId());
        Assertions.assertEquals("shop2", response.getData().getShop().getName());
        Assertions.assertEquals(DataStatus.PENDING.getName(), response.getData().getStatus());
        Assertions.assertEquals("??????", response.getData().getAddress());
        Assertions.assertEquals(Arrays.asList(4L, 5L),
                response.getData().getGoods().stream().map(Goods::getId).collect(toList())
        );
        Assertions.assertEquals(Arrays.asList(3, 5),
                response.getData().getGoods().stream().map(GoodsWithNumber::getNumber).collect(toList())
        );

        // ?????????????????????????????????
        RpcOrderGoods mockRpcOrderGoods = new RpcOrderGoods();
        Order order = new Order();
        order.setId(12345L);
        order.setUserId(1L);
        order.setShopId(2L);
        mockRpcOrderGoods.setOrder(order);
        mockRpcOrderGoods.setGoods(Arrays.asList(goodsInfo1, goodsInfo2));
        when(mockOrderRpcService.orderRpcService.getOrderById(12345L)).thenReturn(mockRpcOrderGoods);

        Response<OrderResponse> getResponse = doHttpRequest("/api/v1/order/12345", "GET", null, loginResponse.cookie)
                .assertOkStatusCode()
                .asJsonObject(new TypeReference<Response<OrderResponse>>() {
                });
        Assertions.assertEquals(12345L, getResponse.getData().getId());
        Assertions.assertEquals(2L, getResponse.getData().getShopId());
        Assertions.assertEquals(2L, getResponse.getData().getShop().getId());
        Assertions.assertEquals(Arrays.asList(4L, 5L),
                getResponse.getData().getGoods().stream().map(GoodsWithNumber::getId).collect(toList()));
        Assertions.assertEquals(Arrays.asList(3, 5),
                getResponse.getData().getGoods().stream().map(GoodsWithNumber::getNumber).collect(toList()));
    }

    @Test
    public void canRollBackIfDeductStockFailed() throws Exception {
        UserLoginResponse loginResponse = loginAndGetCookie();

        OrderInfo orderInfo = new OrderInfo();
        GoodsInfo goodsInfo1 = new GoodsInfo();
        GoodsInfo goodsInfo2 = new GoodsInfo();

        goodsInfo1.setId(4);
        goodsInfo1.setNumber(3);
        goodsInfo2.setId(5);
        goodsInfo2.setNumber(6);

        orderInfo.setGoods(Arrays.asList(goodsInfo1, goodsInfo2));

        HttpResponse response = doHttpRequest("/api/v1/order", "POST", orderInfo, loginResponse.cookie);
        Assertions.assertEquals(HttpStatus.GONE.value(), response.code);

        // ?????????????????????????????????
        canCreateOrder();
    }

    @Test
    public void canDeleteOrder() throws Exception {
        UserLoginResponse loginResponse = loginAndGetCookie();

        when(mockOrderRpcService.orderRpcService.getOrder(anyLong(), anyInt(), anyInt(), any()))
                .thenReturn(mockResponse());

        // ??????????????????
        PageResponse<OrderResponse> orders = doHttpRequest("/api/v1/order?pageSize=2&pageNum=3", "GET", null, loginResponse.cookie)
                .asJsonObject(new TypeReference<PageResponse<OrderResponse>>() {
                });

        Assertions.assertEquals(3, orders.getPageNum());
        Assertions.assertEquals(10, orders.getTotalPage());
        Assertions.assertEquals(2, orders.getPageSize());
        Assertions.assertEquals(Arrays.asList("shop2", "shop2"),
                orders.getData().stream().map(OrderResponse::getShop).map(Shop::getName).collect(toList()));
        Assertions.assertEquals(Arrays.asList("goods3", "goods4"),
                orders.getData().stream()
                        .map(OrderResponse::getGoods)
                        .flatMap(List::stream)
                        .map(Goods::getName)
                        .collect(toList()));

        Assertions.assertEquals(Arrays.asList(5, 3),
                orders.getData().stream()
                        .map(OrderResponse::getGoods)
                        .flatMap(List::stream)
                        .map(GoodsWithNumber::getNumber)
                        .collect(toList()));

        when(mockOrderRpcService.orderRpcService.deleteOrder(100L, 1L))
                .thenReturn(mockRpcOderGoods(100, 1, 3, 2, 5, DataStatus.DELETED));

        // ??????????????????
        Response<OrderResponse> deletedOrder = doHttpRequest("/api/v1/order/100", "DELETE", null, loginResponse.cookie)
                .assertOkStatusCode()
                .asJsonObject(new TypeReference<Response<OrderResponse>>() {
                });

        Assertions.assertEquals(DataStatus.DELETED.getName(), deletedOrder.getData().getStatus());
        Assertions.assertEquals(100L, deletedOrder.getData().getId());
        Assertions.assertEquals(1, deletedOrder.getData().getGoods().size());
        Assertions.assertEquals(3L, deletedOrder.getData().getGoods().get(0).getId());
        Assertions.assertEquals(5, deletedOrder.getData().getGoods().get(0).getNumber());
    }

    @Test
    public void return404IfOrderNotFound() throws Exception {
        UserLoginResponse loginResponse = loginAndGetCookie();

        Order order = new Order();
        order.setId(12345L);
        Assertions.assertEquals(HttpURLConnection.HTTP_NOT_FOUND,
                doHttpRequest("/api/v1/order/1234567", "PATCH", order, loginResponse.cookie).code);
    }

    @Test
    public void canUpdateOrderExpressInfomation() throws Exception {
        UserLoginResponse loginResponse = loginAndGetCookie();

        Order orderUpdateRequest = new Order();
        orderUpdateRequest.setId(12345L);
        orderUpdateRequest.setShopId(2L);
        orderUpdateRequest.setExpressCompany("??????");
        orderUpdateRequest.setExpressId("SF12345678");

        RpcOrderGoods rpcOrderGoods = new RpcOrderGoods();

        Order orderInDB = new Order();
        orderInDB.setId(12345L);
        orderInDB.setShopId(2L);

        rpcOrderGoods.setOrder(orderInDB);

        when(mockOrderRpcService.orderRpcService.getOrderById(12345L)).thenReturn(rpcOrderGoods);
        when(mockOrderRpcService.orderRpcService.updateOrder(any())).thenReturn(
                mockRpcOderGoods(12345L, 1L, 3L, 2L, 10, DataStatus.DELIVERED)
        );

        Response<OrderResponse> response = doHttpRequest("/api/v1/order/12345", "PATCH", orderUpdateRequest, loginResponse.cookie)
                .assertOkStatusCode()
                .asJsonObject(new TypeReference<Response<OrderResponse>>() {
                });

        Assertions.assertEquals(2L, response.getData().getShop().getId());
        Assertions.assertEquals("shop2", response.getData().getShop().getName());
        Assertions.assertEquals(DataStatus.DELIVERED.getName(), response.getData().getStatus());
        Assertions.assertEquals(1, response.getData().getGoods().size());
        Assertions.assertEquals(3, response.getData().getGoods().get(0).getId());
        Assertions.assertEquals(10, response.getData().getGoods().get(0).getNumber());
    }

    @Test
    public void canUpdateOrderStatus() throws Exception {
        UserLoginResponse loginResponse = loginAndGetCookie();

        Order orderUpdateRequest = new Order();
        orderUpdateRequest.setId(12345L);
        orderUpdateRequest.setStatus(DataStatus.RECEIVED.getName());

        RpcOrderGoods rpcOrderGoods = new RpcOrderGoods();
        Order orderInDB = new Order();
        orderInDB.setId(12345L);
        orderInDB.setUserId(1L);
        orderInDB.setShopId(2L);
        rpcOrderGoods.setOrder(orderInDB);

        when(mockOrderRpcService.orderRpcService.getOrderById(12345L)).thenReturn(rpcOrderGoods);
        when(mockOrderRpcService.orderRpcService.updateOrder(any())).thenReturn(
                mockRpcOderGoods(12345L, 1L, 3L, 2L, 10, DataStatus.RECEIVED)
        );

        Response<OrderResponse> response = doHttpRequest("/api/v1/order/12345", "PATCH", orderUpdateRequest, loginResponse.cookie)
                .assertOkStatusCode()
                .asJsonObject(new TypeReference<Response<OrderResponse>>() {
                });

        Assertions.assertEquals(2L, response.getData().getShop().getId());
        Assertions.assertEquals("shop2", response.getData().getShop().getName());
        Assertions.assertEquals(DataStatus.RECEIVED.getName(), response.getData().getStatus());
        Assertions.assertEquals(1, response.getData().getGoods().size());
        Assertions.assertEquals(3, response.getData().getGoods().get(0).getId());
        Assertions.assertEquals(10, response.getData().getGoods().get(0).getNumber());
    }

    private PageResponse<RpcOrderGoods> mockResponse() {
        RpcOrderGoods order1 = mockRpcOderGoods(100, 1, 3, 2, 5, DataStatus.DELIVERED);
        RpcOrderGoods order2 = mockRpcOderGoods(101, 1, 4, 2, 3, DataStatus.RECEIVED);

        return PageResponse.pagedData(3, 2, 10, Arrays.asList(order1, order2));
    }

    private RpcOrderGoods mockRpcOderGoods(long orderId,
                                           long userId,
                                           long goodsId,
                                           long shopId,
                                           int number,
                                           DataStatus status) {
        RpcOrderGoods orderGoods = new RpcOrderGoods();
        Order order = new Order();
        GoodsInfo goodsInfo = new GoodsInfo();

        goodsInfo.setId(goodsId);
        goodsInfo.setNumber(number);

        order.setId(orderId);
        order.setUserId(userId);
        order.setShopId(shopId);
        order.setStatus(status.getName());

        orderGoods.setGoods(Arrays.asList(goodsInfo));
        orderGoods.setOrder(order);
        return orderGoods;
    }
}
