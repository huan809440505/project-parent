package com.hyl.rock.system.controller;


import com.hyl.rock.base.Result;
import com.hyl.rock.constant.UserConstants;
import com.hyl.rock.domain.SysDept;
import com.hyl.rock.log.annotation.Log;
import com.hyl.rock.log.enums.BusinessType;
import com.hyl.rock.security.utils.SecurityUtils;
import com.hyl.rock.system.service.ISysDeptService;
import com.hyl.rock.utils.StringUtils;
import com.hyl.rock.web.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 部门信息
 * 
 */
@Tag(name = "部门信息控制器")
@RestController
@RequestMapping("/dept")
public class SysDeptController extends BaseController
{
    @Autowired
    private ISysDeptService deptService;

    /**
     * 获取部门列表
     */
    @Operation(summary = "获取部门列表")
    @Parameters({
            @Parameter(name = "deptId", description = "部门ID"),
            @Parameter(name = "parentId", description = "父部门ID"),
            @Parameter(name = "deptName", description = "部门名称"),
            @Parameter(name = "status", description = "部门状态:0正常,1停用"),
    })
    @GetMapping("/list")
    public Result<List<SysDept>> list(SysDept dept)
    {
        List<SysDept> deptList = deptService.selectDeptList(dept);
        return success(deptList);
    }

    /**
     * 查询部门列表（排除节点）
     */
    @Operation(summary = "查询部门列表（排除节点）")
    @GetMapping("/list/exclude/{deptId}")
    public Result<List<SysDept>> excludeChild(@PathVariable(value = "deptId", required = false) Long deptId)
    {
        List<SysDept> deptList = deptService.selectDeptList(new SysDept());
        deptList.removeIf(d -> d.getDeptId().intValue() == deptId || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), deptId + ""));
        return success(deptList);
    }

    /**
     * 根据部门编号获取详细信息
     */
    @Operation(summary = "根据部门编号获取详细信息")
    @GetMapping(value = "/{deptId}")
    public Result<SysDept> getInfo(@PathVariable Long deptId)
    {
        deptService.checkDeptDataScope(deptId);
        return success(deptService.selectDeptById(deptId));
    }

    /**
     * 新增部门
     */
    @Operation(summary = "新增部门")
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping
    public Result<String> add(@Validated @RequestBody SysDept dept)
    {
        if (!deptService.checkDeptNameUnique(dept))
        {
            return error("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        dept.setCreateBy(SecurityUtils.getUsername());
        return toAjax(deptService.insertDept(dept));
    }

    /**
     * 修改部门
     */
    @Operation(summary = "修改部门")
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result<String> edit(@Validated @RequestBody SysDept dept)
    {
        Long deptId = dept.getDeptId();
        deptService.checkDeptDataScope(deptId);
        if (!deptService.checkDeptNameUnique(dept))
        {
            return error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        else if (dept.getParentId().equals(deptId))
        {
            return error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        }
        else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getStatus()) && deptService.selectNormalChildrenDeptById(deptId) > 0)
        {
            return error("该部门包含未停用的子部门！");
        }
        dept.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(deptService.updateDept(dept));
    }

    /**
     * 保存部门排序
     */
    @Operation(summary = "保存部门排序")
    @Log(title = "保存部门排序", businessType = BusinessType.UPDATE)
    @PutMapping("/updateSort")
    public Result<String> updateSort(@RequestBody Map<String, String> params)
    {
        String[] deptIds = params.get("deptIds").split(",");
        String[] orderNums = params.get("orderNums").split(",");
        deptService.updateDeptSort(deptIds, orderNums);
        return success();
    }

    /**
     * 删除部门
     */
    @Operation(summary = "删除部门")
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deptId}")
    public Result<String> remove(@PathVariable Long deptId)
    {
        if (deptService.hasChildByDeptId(deptId))
        {
            return warn("存在下级部门,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId))
        {
            return warn("部门存在用户,不允许删除");
        }
        deptService.checkDeptDataScope(deptId);
        return toAjax(deptService.deleteDeptById(deptId));
    }
}
