package com.hyl.rock.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hyl.rock.base.BaseEmptyEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表 sys_menu
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name="菜单权限表")
@TableName("sys_menu")
public class SysMenu extends BaseEmptyEntity {

    /** 菜单ID */
    @Schema(name = "菜单ID")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /** 菜单名称 */
    @NotBlank(message = "菜单名称不能为空")
    @Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
    @Schema(name = "菜单名称")
    @TableField("menu_name")
    private String menuName;

    /** 父菜单名称 */
    @Schema(name = "父菜单名称")
    @TableField("parent_name")
    private String parentName;

    /** 父菜单ID */
    @Schema(name = "父菜单ID")
    @TableField("parent_id")
    private Long parentId;

    /** 显示顺序 */
    @NotNull(message = "显示顺序不能为空")
    @Schema(name = "显示顺序")
    @TableField("order_num")
    private Integer orderNum;

    /** 路由地址 */
    @Size(min = 0, max = 200, message = "路由地址不能超过200个字符")
    @Schema(name = "路由地址")
    @TableField("path")
    private String path;

    /** 组件路径 */
    @Size(min = 0, max = 200, message = "组件路径不能超过255个字符")
    @Schema(name = "组件路径")
    @TableField("component")
    private String component;

    /** 路由参数 */
    @Schema(name = "路由参数")
    @TableField("query")
    private String query;

    /** 路由名称，默认和路由地址相同的驼峰格式（注意：因为vue3版本的router会删除名称相同路由，为避免名字的冲突，特殊情况可以自定义） */
    @Schema(name = "路由名称")
    @TableField("route_name")
    private String routeName;

    /** 是否为外链（0是 1否） */
    @Schema(name = "是否为外链 （0是 1否）")
    @TableField("is_frame")
    private String isFrame;

    /** 是否缓存（0缓存 1不缓存） */
    @Schema(name = "是否缓存（0缓存 1不缓存）")
    @TableField("is_cache")
    private String isCache;

    /** 类型（M目录 C菜单 F按钮） */
    @NotBlank(message = "菜单类型不能为空")
    @Schema(name = "菜单类型（M目录 C菜单 F按钮）")
    @TableField("menu_type")
    private String menuType;

    /** 显示状态（0显示 1隐藏） */
    @Schema(name = "显示状态（0显示 1隐藏）")
    @TableField("visible")
    private String visible;

    /** 菜单状态（0正常 1停用） */
    @Schema(name = "菜单状态（0正常 1停用）")
    @TableField("status")
    private String status;

    /** 权限字符串 */
    @Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符")
    @Schema(name = "权限字符串")
    @TableField("perms")
    private String perms;

    /** 菜单图标 */
    @Schema(name = "菜单图标")
    @TableField("icon")
    private String icon;

    /** 子菜单 */
    @Schema(name = "子菜单")
    @TableField(exist = false)
    private List<SysMenu> children = new ArrayList<SysMenu>();

}
