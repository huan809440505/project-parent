package com.hyl.rock.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hyl.rock.base.Result;
import com.hyl.rock.log.annotation.Log;
import com.hyl.rock.log.enums.BusinessType;
import com.hyl.rock.security.utils.SecurityUtils;
import com.hyl.rock.system.domain.SysNotice;
import com.hyl.rock.system.domain.query.SysNoticeQuery;
import com.hyl.rock.system.service.ISysNoticeReadService;
import com.hyl.rock.system.service.ISysNoticeService;
import com.hyl.rock.text.Convert;
import com.hyl.rock.web.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公告 信息操作处理
 * 
 */
@Tag(name = "公告控制器")
@RestController
@RequestMapping("/notice")
public class SysNoticeController extends BaseController
{
    @Autowired
    private ISysNoticeService noticeService;

    @Autowired
    private ISysNoticeReadService noticeReadService;

    /**
     * 获取通知公告列表
     */
    @Operation(summary = "获取通知公告列表")
    @Parameters({
            @Parameter(name = "current", description = "页码", required = true),
            @Parameter(name = "size", description = "每页数量", required = true),
            @Parameter(name = "noticeTitle", description = "公告标题"),
            @Parameter(name = "noticeType", description = "公告类型（1通知 2公告）"),
            @Parameter(name = "createBy", description = "创建人"),
            @Parameter(name = "startDate",description = "开始日期,格式: yyyy-MM-dd"),
            @Parameter(name = "endDate",description = "结束日期,格式: yyyy-MM-dd")
    })
    @GetMapping("/list")
    public Result<IPage<SysNotice>> list(IPage page, SysNoticeQuery query)
    {
        IPage<SysNotice> pageResult = noticeService.selectNoticePage(page,query);
        return success(pageResult);
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @Operation(summary = "根据通知公告编号获取详细信息")
    @GetMapping(value = "/{noticeId}")
    public Result<SysNotice> getInfo(@PathVariable Long noticeId)
    {
        return success(noticeService.selectNoticeById(noticeId));
    }

    /**
     * 新增通知公告
     */
    @Operation(summary = "新增通知公告")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping
    public Result<String> add(@Validated @RequestBody SysNotice notice)
    {
        notice.setCreateBy(SecurityUtils.getUsername());
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改通知公告
     */
    @Operation(summary = "修改通知公告")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result<String> edit(@Validated @RequestBody SysNotice notice)
    {
        notice.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 首页顶部公告列表（返回全部正常公告，带当前用户已读标记，最多5条）
     */
    @Operation(summary = "首页顶部公告列表")
    @GetMapping("/listTop")
    @ResponseBody
    public Result<Map<String, Object>> listTop()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysNotice> list = noticeReadService.selectNoticeListWithReadStatus(userId, 5);
        long unreadCount = list.stream().filter(n -> !n.getIsRead()).count();
        Map<String,Object> map = new HashMap<>();
        map.put("unreadCount", unreadCount);
        map.put("data", list);
        return success(map);
    }

    /**
     * 标记公告已读
     */
    @Operation(summary = "标记公告已读")
    @PostMapping("/markRead")
    @ResponseBody
    public Result<String> markRead(Long noticeId)
    {
        Long userId = SecurityUtils.getUserId();
        noticeReadService.markRead(noticeId, userId);
        return success();
    }

    /**
     * 批量标记已读
     */
    @Operation(summary = "批量标记已读")
    @PostMapping("/markReadAll")
    @ResponseBody
    public Result<String> markReadAll(String ids)
    {
        Long userId = SecurityUtils.getUserId();
        Long[] noticeIds = Convert.toLongArray(ids);
        noticeReadService.markReadBatch(userId, noticeIds);
        return success();
    }

    /**
     * 已读用户列表数据
     */
    @Operation(summary = "已读用户列表数据")
    @GetMapping("/readUsers/list")
    @ResponseBody
    public Result<IPage<Map<String, Object>>> readUsersList(IPage page,Long noticeId, String searchValue)
    {
        IPage<Map<String, Object>> pageResult = noticeReadService.selectReadUsersByNoticeId(page,noticeId, searchValue);
        return success(pageResult);
    }

    /**
     * 删除通知公告
     */
    @Operation(summary = "删除通知公告")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeIds}")
    public Result<String> remove(@PathVariable Long[] noticeIds)
    {
        noticeReadService.deleteByNoticeIds(noticeIds);
        return toAjax(noticeService.deleteNoticeByIds(noticeIds));
    }
}
