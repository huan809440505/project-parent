package com.hyl.rock.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户和角色关联 sys_user_role
 */
@Data
@ApiModel(value = "用户和角色关联")
@TableName("sys_user_role")
public class SysUserRole implements Serializable {

    /** 用户ID */
    @ApiModelProperty(name = "用户ID")
    @TableField("user_id")
    private Long userId;

    /** 角色ID */
    @ApiModelProperty(name = "角色ID")
    @TableField("role_id")
    private Long roleId;
}
