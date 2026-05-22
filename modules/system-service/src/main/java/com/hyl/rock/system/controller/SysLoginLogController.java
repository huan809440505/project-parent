package com.hyl.rock.system.controller;


import com.hyl.rock.constant.CacheConstants;
import com.hyl.rock.domain.SysLoginLog;
import com.hyl.rock.entity.page.TableDataInfo;
import com.hyl.rock.log.annotation.Log;
import com.hyl.rock.log.enums.BusinessType;
import com.hyl.rock.redis.service.RedisService;
import com.hyl.rock.system.service.ISysLoginLogService;
import com.hyl.rock.utils.poi.ExcelUtil;
import com.hyl.rock.web.controller.BaseController;
import com.hyl.rock.web.domain.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统访问记录
 * 
 */
@Tag(name = "系统访问记录控制器")
@RestController
@RequestMapping("/loginLog")
public class SysLoginLogController extends BaseController
{
    @Autowired
    private ISysLoginLogService loginInForService;

    @Autowired
    private RedisService redisService;

    @Operation(summary = "获取系统访问记录列表")
    @GetMapping("/list")
    public TableDataInfo list(SysLoginLog loginInFor)
    {
        startPage();
        List<SysLoginLog> list = loginInForService.selectLoginLogList(loginInFor);
        return getDataTable(list);
    }

    @Operation(summary = "导出登录日志")
    @Log(title = "登录日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysLoginLog loginInFor)
    {
        List<SysLoginLog> list = loginInForService.selectLoginLogList(loginInFor);
        ExcelUtil<SysLoginLog> util = new ExcelUtil<>(SysLoginLog.class);
        util.exportExcel(response, list, "登录日志");
    }

    @Operation(summary = "删除登录日志")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds)
    {
        return toAjax(loginInForService.deleteLoginLogByIds(infoIds));
    }

    @Operation(summary = "清空登录日志")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/clean")
    public AjaxResult clean()
    {
        loginInForService.cleanLoginLog();
        return success();
    }

    @Operation(summary = "账户解锁")
    @Log(title = "账户解锁", businessType = BusinessType.OTHER)
    @GetMapping("/unlock/{userName}")
    public AjaxResult unlock(@PathVariable("userName") String userName)
    {
        redisService.deleteObject(CacheConstants.PWD_ERR_CNT_KEY + userName);
        return success();
    }

    @Operation(summary = "添加登陆日志")
    @PostMapping
    public AjaxResult add(@RequestBody SysLoginLog loginInFor)
    {
        return toAjax(loginInForService.insertLoginLog(loginInFor));
    }
}
