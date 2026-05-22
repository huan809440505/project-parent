package com.hyl.rock.api.factory;

import com.hyl.rock.api.RemoteErrLogService;
import com.hyl.rock.base.Result;
import com.hyl.rock.domain.SysErrLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 错误日志服务降级处理
 *
 */
@Component
public class RemoteErrLogFallbackFactory implements FallbackFactory<RemoteErrLogService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteErrLogFallbackFactory.class);

    @Override
    public RemoteErrLogService create(Throwable throwable) {
        log.error("错误日志服务调用失败:{}", throwable.getMessage());
        return new RemoteErrLogService() {
            @Override
            public Result<Boolean> saveErrLog(SysErrLog sysErrLog, String source) throws Exception {
                return Result.fail("保存错误日志失败:" + throwable.getMessage());
            }
        };
    }
}
