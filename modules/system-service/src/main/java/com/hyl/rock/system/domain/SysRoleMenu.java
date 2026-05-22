package com.hyl.rock.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色和菜单关联 sys_role_menu
 */
@Data
@Schema(name = "角色和菜单关联")
@TableName("sys_role_menu")
public class SysRoleMenu implements Serializable {

    /** 角色ID */
    @Schema(name = "角色ID")
    @TableField("role_id")
    private Long roleId;

    /** 菜单ID */
    @Schema(name = "菜单ID")
    @TableField("menu_id")
    private Long menuId;
}
