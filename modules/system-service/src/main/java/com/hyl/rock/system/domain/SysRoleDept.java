package com.hyl.rock.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色和部门关联 sys_role_dept
 */
@Data
@Schema(name = "角色和部门关联")
@TableName("sys_role_dept")
public class SysRoleDept implements Serializable {

    /** 角色ID */
    @Schema(name = "角色ID")
    @TableField("role_id")
    private Long roleId;

    /** 部门ID */
    @Schema(name = "部门ID")
    @TableField("dept_id")
    private Long deptId;
}
