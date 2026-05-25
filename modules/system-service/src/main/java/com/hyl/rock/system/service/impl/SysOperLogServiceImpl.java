package com.hyl.rock.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hyl.rock.domain.SysOperLog;
import com.hyl.rock.system.domain.query.SysOperLogQuery;
import com.hyl.rock.system.mapper.SysOperLogMapper;
import com.hyl.rock.system.service.ISysOperLogService;
import com.hyl.rock.utils.DateUtils;
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
    public IPage<SysOperLog> selectOperLogPage(IPage page, SysOperLogQuery query) {

        return operLogMapper.selectPage(page, new LambdaQueryWrapper<SysOperLog>()
                .like(StringUtils.isNotBlank(query.getOperIp()), SysOperLog::getOperIp, query.getOperIp())
                .like(StringUtils.isNotBlank(query.getTitle()), SysOperLog::getTitle, query.getTitle())
                .eq(query.getBusinessType()!=null, SysOperLog::getBusinessType, query.getBusinessType())
                .in(query.getBusinessTypes()!=null&& !query.getBusinessTypes().isEmpty(), SysOperLog::getBusinessType,query.getBusinessTypes())
                .eq(query.getStatus()!=null, SysOperLog::getStatus, query.getStatus())
                .like(StringUtils.isNotBlank(query.getOperName()), SysOperLog::getOperName, query.getOperName())
                .gt(StringUtils.isNotBlank(query.getStartDate()),SysOperLog::getOperTime, DateUtils.getDayStartStr(query.getStartDate()))
                .lt(StringUtils.isNotBlank(query.getEndDate()),SysOperLog::getOperTime,DateUtils.getDayEndStr(query.getEndDate()))
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
