package com.hyl.rock.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hyl.rock.domain.SysOperLog;
import com.hyl.rock.system.mapper.SysOperLogMapper;
import com.hyl.rock.system.service.ISysOperLogService;
import com.hyl.rock.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作日志 服务层处理
 * 
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService
{
    @Autowired
    private SysOperLogMapper operLogMapper;

    /**
     * 新增操作日志
     * 
     * @param operLog 操作日志对象
     * @return 结果
     */
    @Override
    public int insertOperLog(SysOperLog operLog)
    {
        return operLogMapper.insertOperLog(operLog);
    }

    /**
     * 查询系统操作日志集合
     * 
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog)
    {
        return operLogMapper.selectOperLogList(operLog);
    }

    @Override
    public IPage<SysOperLog> selectOperLogPage(IPage page, SysOperLog operLog) {
        String beginTime = "";
        if(operLog.getParams().get("beginTime")!=null){
            beginTime = operLog.getParams().get("beginTime").toString();
        }
        String endTime = "";
        if(operLog.getParams().get("endTime")!=null){
            endTime = operLog.getParams().get("endTime").toString();
        }
        return operLogMapper.selectPage(page, new LambdaQueryWrapper<SysOperLog>()
                .like(StringUtils.isNotBlank(operLog.getOperIp()), SysOperLog::getOperIp, operLog.getOperIp())
                .like(StringUtils.isNotBlank(operLog.getTitle()), SysOperLog::getTitle, operLog.getTitle())
                .eq(operLog.getBusinessType()!=null, SysOperLog::getBusinessType, operLog.getBusinessType())
                .in(operLog.getBusinessTypes()!=null&&operLog.getBusinessTypes().length>0, SysOperLog::getBusinessType,operLog.getBusinessTypes())
                .eq(operLog.getStatus()!=null, SysOperLog::getStatus, operLog.getStatus())
                .like(StringUtils.isNotBlank(operLog.getOperName()), SysOperLog::getOperName, operLog.getOperName())
                .gt(StringUtils.isNotBlank(beginTime),SysOperLog::getOperTime,beginTime)
                .lt(StringUtils.isNotBlank(endTime),SysOperLog::getOperTime,endTime)
                .orderByDesc(SysOperLog::getOperId)
        );
    }

    /**
     * 批量删除系统操作日志
     * 
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    @Override
    public int deleteOperLogByIds(Long[] operIds)
    {
        return operLogMapper.deleteOperLogByIds(operIds);
    }

    /**
     * 查询操作日志详细
     * 
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLog selectOperLogById(Long operId)
    {
        return operLogMapper.selectOperLogById(operId);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog()
    {
        operLogMapper.cleanOperLog();
    }
}
