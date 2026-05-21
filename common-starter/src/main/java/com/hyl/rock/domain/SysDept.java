package com.hyl.rock.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.hyl.rock.base.BaseEmptyEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门表 sys_dept
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "部门表")
@TableName("sys_dept")
public class SysDept extends BaseEmptyEntity {

    /** 部门ID */
    @ApiModelProperty(name = "部门ID")
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;

    /** 父部门ID */
    @ApiModelProperty(name = "父部门ID")
    @TableField("parent_id")
    private Long parentId;

    /** 祖级列表 */
    @ApiModelProperty(name = "祖级列表")
    @TableField("ancestors")
    private String ancestors;

    /** 部门名称 */
    @ApiModelProperty(name = "部门名称")
    @TableField("dept_name")
    private String deptName;

    /** 显示顺序 */
    @ApiModelProperty(name = "显示顺序")
    @TableField("order_num")
    private Integer orderNum;

    /** 负责人 */
    @ApiModelProperty(name = "负责人")
    @TableField("leader")
    private String leader;

    /** 联系电话 */
    @ApiModelProperty(name = "联系电话")
    @TableField("phone")
    private String phone;

    /** 邮箱 */
    @ApiModelProperty(name = "邮箱")
    @TableField("email")
    private String email;

    /** 部门状态:0正常,1停用 */
    @ApiModelProperty(name = "部门状态:0正常,1停用")
    @TableField("status")
    private String status;

    /** 是否删除（0代表存在 1代表删除） */
    @ApiModelProperty(name = "是否删除（0代表存在 1代表删除）")
    @TableField("is_delete")
    @TableLogic
    private Boolean isDelete;

    /** 父部门名称 */
    @ApiModelProperty(name = "父部门名称")
    @TableField(exist = false)
    private String parentName;

    /** 子部门 */
    @ApiModelProperty(name = "子部门")
    @TableField(exist = false)
    private List<SysDept> children = new ArrayList<SysDept>();
}
