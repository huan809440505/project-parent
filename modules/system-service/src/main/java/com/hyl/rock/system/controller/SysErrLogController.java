package com.hyl.rock.system.controller;

import com.hyl.rock.domain.SysErrLog;
import com.hyl.rock.entity.page.TableDataInfo;
import com.hyl.rock.system.domain.query.SysErrLogQuery;
import com.hyl.rock.system.service.ISysErrLogService;
import com.hyl.rock.web.controller.BaseController;
import com.hyl.rock.web.domain.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public TableDataInfo list(SysErrLogQuery query) {
        startPage();
        List<SysErrLog> list = sysErrLogService.listSysErrLog(query);
        return getDataTable(list);
    }

    @Operation(summary = "查询错误日志详情")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(sysErrLogService.getSysErrLog(id));
    }

    @Operation(summary = "新增错误日志")
    @PostMapping
    public AjaxResult add(@RequestBody SysErrLog sysErrLog){
        return toAjax(sysErrLogService.insertSysErrLog(sysErrLog));
    }
}
