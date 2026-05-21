package com.hyl.rock.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.hyl.rock.base.BaseEmptyEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * 角色表 sys_role
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "角色表")
@TableName("sys_role")
public class SysRole extends BaseEmptyEntity {

    /** 角色ID */
    @ApiModelProperty(name = "角色ID")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /** 角色名称 */
    @ApiModelProperty(name = "角色名称")
    @TableField("role_name")
    private String roleName;

    /** 角色权限 */
    @ApiModelProperty(name = "角色权限")
    @TableField("role_key")
    private String roleKey;

    /** 角色排序 */
    @ApiModelProperty(name = "角色排序")
    @TableField("role_sort")
    private Integer roleSort;

    /** 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限） */
    @ApiModelProperty(name = "数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限）")
    @TableField("data_scope")
    private String dataScope;

    /** 菜单树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示） */
    @ApiModelProperty(name = "菜单树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示）")
    @TableField(exist = false)
    private boolean menuCheckStrictly;

    /** 部门树选择项是否关联显示（0：父子不互相关联显示 1：父子互相关联显示 ） */
    @ApiModelProperty(name = "部门树选择项是否关联显示（0：父子不互相关联显示 1：父子互相关联显示 ）")
    @TableField(exist = false)
    private boolean deptCheckStrictly;

    /** 角色状态（0正常 1停用） */
    @ApiModelProperty(name = "角色状态（0正常 1停用）")
    @TableField("status")
    private String status;

    /** 是否删除（0代表存在 1代表删除） */
    @ApiModelProperty(name = "是否删除（0代表存在 1代表删除）")
    @TableField("is_delete")
    @TableLogic
    private Boolean isDelete;

    /** 用户是否存在此角色标识 默认不存在 */
    @ApiModelProperty(name = "用户是否存在此角色标识 默认不存在")
    @TableField(exist = false)
    private boolean flag = false;

    /** 菜单组 */
    @ApiModelProperty(name = "菜单组")
    @TableField(exist = false)
    private Long[] menuIds;

    /** 部门组（数据权限） */
    @ApiModelProperty(name = "部门组")
    @TableField(exist = false)
    private Long[] deptIds;

    /** 角色菜单权限 */
    @ApiModelProperty(name = "角色菜单权限")
    @TableField(exist = false)
    private Set<String> permissions;

    public SysRole() {

    }

    public SysRole(Long roleId) {
        this.roleId = roleId;
    }

    public boolean isAdmin() {
        return isAdmin(this.roleId);
    }

    public static boolean isAdmin(Long roleId) {
        return roleId != null && 1L == roleId;
    }
}
