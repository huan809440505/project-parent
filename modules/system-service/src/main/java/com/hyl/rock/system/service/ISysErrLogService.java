package com.hyl.rock.system.service;

import com.hyl.rock.domain.SysErrLog;
import com.hyl.rock.system.domain.query.SysErrLogQuery;

import java.util.List;

public interface ISysErrLogService {

    int insertSysErrLog(SysErrLog sysErrLog);

    List<SysErrLog> listSysErrLog(SysErrLogQuery query);

    SysErrLog getSysErrLog(Long id);
}
