package com.hyl.test.client.order;

import com.hyl.test.base.Result;
import com.hyl.test.entity.Order;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "orderClient",url = "/order-service/order")
public interface OrderClient {

    @ApiOperation("获取订单信息")
    @GetMapping("/{id}")
    Result<Order> getOrder(@PathVariable("id") Long id);
}
