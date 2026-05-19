package com.hyl.rock.client.product;

import com.hyl.rock.base.Result;
import com.hyl.rock.entity.Product;
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
