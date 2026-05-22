package com.hyl.rock.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户和角色关联 sys_user_role
 */
@Data
@Schema(name = "用户和角色关联")
@TableName("sys_user_role")
public class SysUserRole implements Serializable {

    /** 用户ID */
    @Schema(name = "用户ID")
    @TableField("user_id")
    private Long userId;

    /** 角色ID */
    @Schema(name = "角色ID")
    @TableField("role_id")
    private Long roleId;
}
