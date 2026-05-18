package com.hyl.test.controller;

import com.hyl.test.base.Result;
import com.hyl.test.entity.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "商品管理")
@RestController
@RequestMapping("/product")
public class ProductController {

    @ApiOperation("获取商品信息")
    @GetMapping("/{id}")
    public Result<Product> getProduct(@PathVariable("id")Long id){
        Product product = new Product();
        product.setId(id);
        product.setProductNo("123456");
        product.setProductName("测试商品");
        return Result.success(product);
    }
}
