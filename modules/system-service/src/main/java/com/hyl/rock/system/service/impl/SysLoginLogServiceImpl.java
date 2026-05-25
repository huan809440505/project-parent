package com.hyl.rock.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hyl.rock.domain.SysLoginLog;
import com.hyl.rock.system.domain.query.SysLoginLogQuery;
import com.hyl.rock.system.mapper.SysLoginLogMapper;
import com.hyl.rock.system.service.ISysLoginLogService;
import com.hyl.rock.utils.DateUtils;
import com.hyl.rock.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层处理
 * 
 */
@Service
public class SysLoginLogServiceImpl implements ISysLoginLogService
{

    @Autowired
    private SysLoginLogMapper loginInForMapper;

    /**
     * 新增系统登录日志
     * 
     * @param loginInFor 访问日志对象
     */
    @Override
    public int insertLoginLog(SysLoginLog loginInFor)
    {
        return loginInForMapper.insertLoginLog(loginInFor);
    }

    /**
     * 查询系统登录日志集合
     * 
     * @param loginInFor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLoginLog> selectLoginLogList(SysLoginLog loginInFor)
    {
        return loginInForMapper.selectLoginLogList(loginInFor);
    }

    @Override
    public IPage<SysLoginLog> selectLoginLogPage(IPage page, SysLoginLogQuery query) {

        return loginInForMapper.selectPage(page,new LambdaQueryWrapper<SysLoginLog>()
                .like(StringUtils.isNotBlank(query.getIpaddr()), SysLoginLog::getIpaddr, query.getIpaddr())
                .eq(StringUtils.isNotBlank(query.getStatus()), SysLoginLog::getStatus, query.getStatus())
                .like(StringUtils.isNotBlank(query.getUserName()), SysLoginLog::getUserName, query.getUserName())
                .ge(StringUtils.isNotEmpty(query.getStartDate()),SysLoginLog::getCreateTime, DateUtils.getDayStartStr(query.getStartDate()))
                .le(StringUtils.isNotEmpty(query.getEndDate()),SysLoginLog::getCreateTime, DateUtils.getDayStartStr(query.getEndDate()))
        );
    }

    /**
     * 批量删除系统登录日志
     * 
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
    @Override
    public int deleteLoginLogByIds(Long[] infoIds)
    {
        return loginInForMapper.deleteLoginLogByIds(infoIds);
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLoginLog()
    {
        loginInForMapper.cleanLoginLog();
    }
}
