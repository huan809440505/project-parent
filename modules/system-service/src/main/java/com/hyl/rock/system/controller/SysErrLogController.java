package com.hyl.rock.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hyl.rock.base.Result;
import com.hyl.rock.domain.SysErrLog;
import com.hyl.rock.system.domain.query.SysErrLogQuery;
import com.hyl.rock.system.service.ISysErrLogService;
import com.hyl.rock.web.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 错误日志 信息操作处理
 *
 */
@Tag(name = "错误日志控制器",description = "错误日志描述")
@RestController
@RequestMapping("/errLog")
public class SysErrLogController extends BaseController {

    @Autowired
    private ISysErrLogService sysErrLogService;

    @Operation(summary = "分页查询")
    @GetMapping("/list")
    public Result<IPage<SysErrLog>> list(IPage page, SysErrLogQuery query) {
        IPage<SysErrLog> pageResult = sysErrLogService.selectSysErrLogPage(page,query);
        return success(pageResult);
    }

    @Operation(summary = "查询错误日志详情")
    @GetMapping("/{id}")
    public Result<SysErrLog> getInfo(@PathVariable Long id) {
        return success(sysErrLogService.getSysErrLog(id));
    }

    @Operation(summary = "新增错误日志")
    @PostMapping
    public Result<String> add(@RequestBody SysErrLog sysErrLog){
        return toAjax(sysErrLogService.insertSysErrLog(sysErrLog));
    }
}
