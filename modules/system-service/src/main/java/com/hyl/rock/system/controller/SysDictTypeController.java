package com.hyl.rock.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hyl.rock.base.Result;
import com.hyl.rock.domain.SysDictType;
import com.hyl.rock.log.annotation.Log;
import com.hyl.rock.log.enums.BusinessType;
import com.hyl.rock.security.utils.SecurityUtils;
import com.hyl.rock.system.domain.query.SysDictTypeQuery;
import com.hyl.rock.system.service.ISysDictTypeService;
import com.hyl.rock.utils.ExportUtils;
import com.hyl.rock.web.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典信息
 * 
 */
@Tag(name = "数据字典类型控制器")
@RestController
@RequestMapping("/dict/type")
public class SysDictTypeController extends BaseController
{
    @Autowired
    private ISysDictTypeService dictTypeService;

    @Operation(summary = "获取数据字典类型列表")
    @Parameters({
            @Parameter(name = "current", description = "页码", required = true),
            @Parameter(name = "size", description = "每页数量", required = true),
            @Parameter(name = "dictType", description = "字典类型"),
            @Parameter(name = "startDate",description = "开始日期,格式: yyyy-MM-dd"),
            @Parameter(name = "endDate",description = "结束日期,格式: yyyy-MM-dd")
    })
    @GetMapping("/list")
    public Result<IPage<SysDictType>> list(IPage page, SysDictTypeQuery query)
    {
        IPage<SysDictType> pageResult = dictTypeService.selectDictTypePage(page,query);
        return success(pageResult);
    }

    @Operation(summary = "导出数据字典类型列表")
    @Log(title = "字典类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysDictTypeQuery dictType)
    {
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        ExportUtils.exportExcelWithStyle(list,SysDictType.class,"字典类型");
    }

    /**
     * 查询字典类型详细
     */
    @Operation(summary = "查询字典类型详细")
    @GetMapping(value = "/{dictId}")
    public Result<SysDictType> getInfo(@PathVariable Long dictId)
    {
        return success(dictTypeService.selectDictTypeById(dictId));
    }

    /**
     * 新增字典类型
     */
    @Operation(summary = "新增字典类型")
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @PostMapping
    public Result<String> add(@Validated @RequestBody SysDictType dict)
    {
        if (!dictTypeService.checkDictTypeUnique(dict))
        {
            return error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setCreateBy(SecurityUtils.getUsername());
        return toAjax(dictTypeService.insertDictType(dict));
    }

    /**
     * 修改字典类型
     */
    @Operation(summary = "修改字典类型")
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result<String> edit(@Validated @RequestBody SysDictType dict)
    {
        if (!dictTypeService.checkDictTypeUnique(dict))
        {
            return error("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(dictTypeService.updateDictType(dict));
    }

    /**
     * 删除字典类型
     */
    @Operation(summary = "删除字典类型")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictIds}")
    public Result<String> remove(@PathVariable Long[] dictIds)
    {
        dictTypeService.deleteDictTypeByIds(dictIds);
        return success();
    }

    /**
     * 刷新字典缓存
     */
    @Operation(summary = "刷新字典缓存")
    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
    @DeleteMapping("/refreshCache")
    public Result<String> refreshCache()
    {
        dictTypeService.resetDictCache();
        return success();
    }

    /**
     * 获取字典选择框列表
     */
    @Operation(summary = "获取字典选择框列表")
    @GetMapping("/optionSelect")
    public Result<List<SysDictType>> optionSelect()
    {
        List<SysDictType> dictTypes = dictTypeService.selectDictTypeAll();
        return success(dictTypes);
    }
}
