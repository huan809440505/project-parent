package com.hyl.rock.log.service;

import com.hyl.rock.constant.SecurityConstants;
import com.hyl.rock.entity.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步调用日志服务
 * 
 */
@Service
public class AsyncLogService
{


    /**
     * 保存系统日志记录
     */
    @Async
    public void saveSysLog(SysOperLog sysOperLog) throws Exception
    {

    }
}
