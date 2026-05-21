package com.hyl.rock.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色和菜单关联 sys_role_menu
 */
@Data
@ApiModel(value = "角色和菜单关联")
@TableName("sys_role_menu")
public class SysRoleMenu implements Serializable {

    /** 角色ID */
    @ApiModelProperty(name = "角色ID")
    @TableField("role_id")
    private Long roleId;

    /** 菜单ID */
    @ApiModelProperty(name = "菜单ID")
    @TableField("menu_id")
    private Long menuId;
}
