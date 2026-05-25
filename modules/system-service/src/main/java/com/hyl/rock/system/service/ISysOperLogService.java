package com.hyl.rock.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hyl.rock.domain.SysOperLog;
import com.hyl.rock.system.domain.query.SysOperLogQuery;

import java.util.List;

/**
 * 操作日志 服务层
 * 
 */
public interface ISysOperLogService
{
    /**
     * 新增操作日志
     * 
     * @param operLog 操作日志对象
     * @return 结果
     */
    public int insertOperLog(SysOperLog operLog);

    /**
     * 查询系统操作日志集合
     * 
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    public List<SysOperLog> selectOperLogList(SysOperLog operLog);


    public IPage<SysOperLog> selectOperLogPage(IPage page, SysOperLogQuery query);

    /**
     * 批量删除系统操作日志
     * 
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    public int deleteOperLogByIds(Long[] operIds);

    /**
     * 查询操作日志详细
     * 
     * @param operId 操作ID
     * @return 操作日志对象
     */
    public SysOperLog selectOperLogById(Long operId);

    /**
     * 清空操作日志
     */
    public void cleanOperLog();
}
