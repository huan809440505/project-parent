package com.hyl.rock.system.service.impl;


import com.hyl.rock.domain.SysLoginInFor;
import com.hyl.rock.system.mapper.SysLoginInForMapper;
import com.hyl.rock.system.service.ISysLoginInForService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层处理
 * 
 */
@Service
public class SysLoginInForServiceImpl implements ISysLoginInForService
{

    @Autowired
    private SysLoginInForMapper loginInForMapper;

    /**
     * 新增系统登录日志
     * 
     * @param loginInFor 访问日志对象
     */
    @Override
    public int insertLoginInFor(SysLoginInFor loginInFor)
    {
        return loginInForMapper.insertLoginInFor(loginInFor);
    }

    /**
     * 查询系统登录日志集合
     * 
     * @param loginInFor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLoginInFor> selectLogininforList(SysLoginInFor loginInFor)
    {
        return loginInForMapper.selectLoginInForList(loginInFor);
    }

    /**
     * 批量删除系统登录日志
     * 
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
    @Override
    public int deleteLoginInForByIds(Long[] infoIds)
    {
        return loginInForMapper.deleteLoginInForByIds(infoIds);
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLoginInFor()
    {
        loginInForMapper.cleanLoginInFor();
    }
}
