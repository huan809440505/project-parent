package com.hyl.rock.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyl.rock.domain.SysLoginLog;
import com.hyl.rock.system.domain.query.SysLoginLogQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统访问日志情况信息 数据层
 * 
 */
@Mapper
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {
    /**
     * 新增系统登录日志
     * 
     * @param loginLog 访问日志对象
     */
    public int insertLoginLog(SysLoginLog loginLog);

    /**
     * 查询系统登录日志集合
     * 
     * @param loginLog 访问日志对象
     * @return 登录记录集合
     */
    public List<SysLoginLog> selectLoginLogList(SysLoginLogQuery loginLog);

    /**
     * 批量删除系统登录日志
     * 
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
    public int deleteLoginLogByIds(Long[] infoIds);

    /**
     * 清空系统登录日志
     * 
     * @return 结果
     */
    public int cleanLoginLog();
}
