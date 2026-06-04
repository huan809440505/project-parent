package com.hyl.rock.base;

import com.hyl.rock.domain.SysUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Schema(name = "用户登陆信息")
public class LoginUser implements Serializable {

    /**
     * 用户唯一标识
     */
    @Schema(name = "用户唯一标识")
    private String token;

    /**
     * 用户名id
     */
    @Schema(name ="用户名id")
    private Long userid;

    /**
     * 用户名
     */
    @Schema(name ="用户名")
    private String username;

    /**
     * 登录时间
     */
    @Schema(name ="登录时间")
    private Long loginTime;

    /**
     * 过期时间
     */
    @Schema(name ="过期时间")
    private Long expireTime;

    /**
     * 登录IP地址
     */
    @Schema(name ="登录IP地址")
    private String ipaddr;

    /**
     * 权限列表
     */
    @Schema(name ="权限列表")
    private Set<String> permissions;

    /**
     * 角色列表
     */
    @Schema(name ="角色列表")
    private Set<String> roles;

    /**
     * 用户信息
     */
    @Schema(name ="用户信息")
    private SysUser sysUser;
}
