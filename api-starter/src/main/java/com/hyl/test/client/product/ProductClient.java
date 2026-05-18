package com.hyl.test.client.product;

import com.hyl.test.base.Result;
import com.hyl.test.entity.Product;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productClient",url = "/product-service/product")
public interface ProductClient {

    @ApiOperation("获取商品信息")
    @GetMapping("/{id}")
    Result<Product> getProduct(@PathVariable("id") Long id);
}
