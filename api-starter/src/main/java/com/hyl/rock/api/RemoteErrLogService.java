package com.hyl.rock.api;

import com.hyl.rock.api.factory.RemoteLogFallbackFactory;
import com.hyl.rock.base.Result;
import com.hyl.rock.constant.SecurityConstants;
import com.hyl.rock.domain.SysErrLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 错误日志服务
 */
@FeignClient(contextId = "remoteErrLogService", value = "system-service", fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteErrLogService {

    @PostMapping("/errLog")
    Result<Boolean> saveErrLog(@RequestBody SysErrLog sysErrLog,@RequestHeader(SecurityConstants.FROM_SOURCE) String source) throws Exception;
}
