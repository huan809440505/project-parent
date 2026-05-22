package com.hyl.rock.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyl.rock.domain.SysErrLog;
import com.hyl.rock.system.domain.query.SysErrLogQuery;
import com.hyl.rock.system.mapper.SysErrLogMapper;
import com.hyl.rock.system.service.ISysErrLogService;
import com.hyl.rock.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysErrLogServiceImpl extends ServiceImpl<SysErrLogMapper, SysErrLog> implements ISysErrLogService {

    @Override
    public int insertSysErrLog(SysErrLog sysErrLog) {
        return baseMapper.insert(sysErrLog);
    }

    @Override
    public List<SysErrLog> listSysErrLog(SysErrLogQuery query){
        return baseMapper.selectList(new LambdaQueryWrapper<SysErrLog>()
                .like(StringUtils.isNotBlank(query.getTitle()), SysErrLog::getTitle, query.getTitle())
                .ge(StringUtils.isNotEmpty(query.getStartTime()),SysErrLog::getCreateTime,query.getStartTime())
                .le(StringUtils.isNotEmpty(query.getEndTime()),SysErrLog::getCreateTime,query.getEndTime())
        );
    }

    @Override
    public SysErrLog getSysErrLog(Long id) {
        return baseMapper.selectById(id);
    }

}
