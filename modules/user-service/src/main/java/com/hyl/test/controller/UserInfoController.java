package com.hyl.test.controller;

import com.hyl.test.base.Result;
import com.hyl.test.entity.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "账号管理")
@RestController
@RequestMapping("/user/info")
public class UserInfoController {

    @ApiOperation("获取用户信息")
    @GetMapping("/{id}")
    public Result<UserInfo> getInfo(@PathVariable("id") Long id){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setUsername("hyl");
        userInfo.setPassword("<PASSWORD>");
        return Result.success(new UserInfo());
    }
}
