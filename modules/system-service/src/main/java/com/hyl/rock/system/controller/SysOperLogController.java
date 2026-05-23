package com.hyl.rock.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hyl.rock.base.Result;
import com.hyl.rock.domain.SysOperLog;
import com.hyl.rock.log.annotation.Log;
import com.hyl.rock.log.enums.BusinessType;
import com.hyl.rock.system.service.ISysOperLogService;
import com.hyl.rock.utils.poi.ExcelUtil;
import com.hyl.rock.web.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志记录
 * 
 */
@Tag(name = "操作日志控制器")
@RestController
@RequestMapping("/operLog")
public class SysOperLogController extends BaseController
{
    @Autowired
    private ISysOperLogService operLogService;

    @Operation(summary = "获取操作日志列表")
    @GetMapping("/list")
    public Result<IPage<SysOperLog>> list(IPage page, SysOperLog operLog)
    {
        IPage<SysOperLog> pageResult = operLogService.selectOperLogPage(page,operLog);
        return success(pageResult);
    }

    @Operation(summary = "导出操作日志列表")
    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysOperLog operLog)
    {
        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
        ExcelUtil<SysOperLog> util = new ExcelUtil<>(SysOperLog.class);
        util.exportExcel(response, list, "操作日志");
    }

    @Operation(summary = "删除操作日志")
    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{operIds}")
    public Result<String> remove(@PathVariable Long[] operIds)
    {
        return toAjax(operLogService.deleteOperLogByIds(operIds));
    }

    @Operation(summary = "清空操作日志")
    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public Result<String> clean()
    {
        operLogService.cleanOperLog();
        return success();
    }

    @Operation(summary = "添加操作日志")
    @PostMapping
    public Result<String> add(@RequestBody SysOperLog operLog)
    {
        return toAjax(operLogService.insertOperLog(operLog));
    }
}
