package com.hyl.rock.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hyl.rock.base.Result;
import com.hyl.rock.domain.SysDictData;
import com.hyl.rock.log.annotation.Log;
import com.hyl.rock.log.enums.BusinessType;
import com.hyl.rock.security.utils.SecurityUtils;
import com.hyl.rock.system.service.ISysDictDataService;
import com.hyl.rock.system.service.ISysDictTypeService;
import com.hyl.rock.utils.StringUtils;
import com.hyl.rock.utils.poi.ExcelUtil;
import com.hyl.rock.web.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典信息
 * 
 */
@Tag(name = "数据字典信息控制器")
@RestController
@RequestMapping("/dict/data")
public class SysDictDataController extends BaseController
{
    @Autowired
    private ISysDictDataService dictDataService;
    
    @Autowired
    private ISysDictTypeService dictTypeService;

    @Operation(summary = "获取数据字典列表")
    @Parameters({
            @Parameter(name = "current", description = "页码", required = true),
            @Parameter(name = "size", description = "每页数量", required = true),
            @Parameter(name = "dictType", description = "字典类型")
    })
    @GetMapping("/list")
    public Result<IPage<SysDictData>> list(IPage page,SysDictData dictData)
    {
        IPage<SysDictData> pageResult = dictDataService.selectDictDataPage(page,dictData);
        return success(pageResult);
    }

    @Operation(summary = "导出数据字典列表")
    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDictData dictData)
    {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        ExcelUtil<SysDictData> util = new ExcelUtil<>(SysDictData.class);
        util.exportExcel(response, list, "字典数据");
    }

    /**
     * 查询字典数据详细
     */
    @Operation(summary = "查询字典数据详细")
    @GetMapping(value = "/{dictCode}")
    public Result<SysDictData> getInfo(@PathVariable Long dictCode)
    {
        return success(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @Operation(summary = "根据字典类型查询字典数据信息")
    @GetMapping(value = "/type/{dictType}")
    public Result<List<SysDictData>> dictType(@PathVariable String dictType)
    {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data))
        {
            data = new ArrayList<>();
        }
        return success(data);
    }

    /**
     * 新增字典类型
     */
    @Operation(summary = "新增字典类型")
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping
    public Result<String> add(@Validated @RequestBody SysDictData dict)
    {
        dict.setCreateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
    @Operation(summary = "修改保存字典类型")
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result<String> edit(@Validated @RequestBody SysDictData dict)
    {
        dict.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     */
    @Operation(summary = "删除字典类型")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictCodes}")
    public Result<String> remove(@PathVariable Long[] dictCodes)
    {
        dictDataService.deleteDictDataByIds(dictCodes);
        return success();
    }
}
