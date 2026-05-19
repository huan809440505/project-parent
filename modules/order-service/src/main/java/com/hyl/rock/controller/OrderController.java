package com.hyl.rock.controller;

import com.hyl.rock.base.Result;
import com.hyl.rock.entity.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "订单管理")
@RestController
@RequestMapping("/order")
public class OrderController {

    @ApiOperation("获取订单信息")
    @GetMapping("/{id}")
    public Result<Order> getOrder(@PathVariable("id")Long id){
        Order order = new Order();
        order.setId(id);
        order.setOrderNo("123456");
        order.setUserId(1L);
        order.setProductId(1L);
        return Result.success(order);
    }
}
