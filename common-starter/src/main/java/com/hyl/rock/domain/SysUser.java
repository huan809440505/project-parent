package com.hyl.rock.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hyl.rock.base.BaseEmptyEntity;
import com.hyl.rock.constant.UserConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 用户对象 sys_user
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "用户对象")
@TableName("sys_user")
public class SysUser extends BaseEmptyEntity {

    /** 用户ID */
    @Schema(name = "用户ID")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /** 部门ID */
    @Schema(name = "部门编号")
    @TableField("dept_id")
    private Long deptId;

    /** 用户账号 */
    @Schema(name = "登录名称")
    @TableField("user_name")
    private String userName;

    /** 用户昵称 */
    @Schema(name = "用户名称")
    @TableField("nick_name")
    private String nickName;

    /** 用户邮箱 */
    @Schema(name = "用户邮箱")
    @TableField("email")
    private String email;

    /** 手机号码 */
    @Schema(name = "手机号码")
    @TableField("phone_number")
    private String phoneNumber;

    /** 用户性别(0=男,1=女,2=未知) */
    @Schema(name = "用户性别(0=男,1=女,2=未知)")
    @TableField("sex")
    private String sex;

    /** 用户头像 */
    @Schema(name = "用户头像")
    @TableField("avatar")
    private String avatar;

    /** 密码 */
    @Schema(name = "密码")
    @TableField("password")
    private String password;

    /** 账号状态（0正常 1停用） */
    @Schema(name = "账号状态（0正常 1停用）")
    @TableField("status")
    private String status;

    /** 是否删除（0代表存在 1代表删除） */
    @Schema(name = "是否删除（0代表存在 1代表删除）")
    @TableField("is_delete")
    @TableLogic
    private Boolean isDelete;

    /** 最后登录IP */
    @Schema(name = "最后登录IP")
    @TableField("login_ip")
    private String loginIp;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(name = "最后登录时间")
    @TableField("login_date")
    private Date loginDate;

    /** 密码最后更新时间 */
    @Schema(name = "密码最后更新时间")
    @TableField("pwd_update_date")
    private Date pwdUpdateDate;

    /** 部门对象 */
    @Schema(name = "部门对象")
    @TableField(exist = false)
    private SysDept dept;

    /** 角色对象 */
    @Schema(name = "角色对象")
    @TableField(exist = false)
    private List<SysRole> roles;

    /** 角色组 */
    @Schema(name = "角色组")
    @TableField(exist = false)
    private Long[] roleIds;

    /** 岗位组 */
    @Schema(name = "岗位组")
    @TableField(exist = false)
    private Long[] postIds;

    /** 角色ID */
    @Schema(name = "角色ID")
    @TableField(exist = false)
    private Long roleId;

    public SysUser(){}

    public SysUser(Long userId) {
        this.userId = userId;
    }

    public boolean isAdmin()
    {
        return UserConstants.isAdmin(userId);
    }
}
