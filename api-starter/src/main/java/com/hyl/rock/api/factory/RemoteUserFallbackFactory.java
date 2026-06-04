package com.hyl.rock.api.factory;

import com.hyl.rock.api.RemoteUserService;
import com.hyl.rock.base.Result;
import com.hyl.rock.domain.SysUser;
import com.hyl.rock.base.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 * 
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService()
        {
            @Override
            public Result<LoginUser> getUserInfo(String username, String source)
            {
                return Result.fail("获取用户失败:" + throwable.getMessage());
            }

            @Override
            public Result<Boolean> registerUserInfo(SysUser sysUser, String source)
            {
                return Result.fail("注册用户失败:" + throwable.getMessage());
            }

            @Override
            public Result<Boolean> recordUserLogin(SysUser sysUser, String source)
            {
                return Result.fail("记录用户登录信息失败:" + throwable.getMessage());
            }
        };
    }
}
