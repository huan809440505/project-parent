package com.hyl.rock.api;


import com.hyl.rock.api.factory.RemoteLogFallbackFactory;
import com.hyl.rock.base.Result;
import com.hyl.rock.constant.SecurityConstants;
import com.hyl.rock.domain.SysLoginLog;
import com.hyl.rock.domain.SysOperLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 日志服务
 * 
 */
@FeignClient(contextId = "remoteLogService", value = "system-service", fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteLogService
{
    /**
     * 保存系统日志
     *
     * @param sysOperLog 日志实体
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/operLog")
    Result<Boolean> saveLog(@RequestBody SysOperLog sysOperLog, @RequestHeader(SecurityConstants.FROM_SOURCE) String source) throws Exception;

    /**
     * 保存访问记录
     *
     * @param sysLoginLog 访问实体
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/loginInFor")
    Result<Boolean> saveLoginInFor(@RequestBody SysLoginLog sysLoginLog, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
