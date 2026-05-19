package com.hyl.rock.client.user;

import com.hyl.rock.base.Result;
import com.hyl.rock.entity.UserInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userInfoClient",url = "/user-service/user/info")
public interface UserInfoClient {

    @ApiOperation("获取用户信息")
    @GetMapping("/{id}")
    Result<UserInfo> getInfo(@PathVariable("id") Long id);
}
