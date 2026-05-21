package com.hyl.rock.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 在线用户 sys_user_online
 */
@Data
@ApiModel(value = "在线用户")
public class SysUserOnline implements Serializable {

    /** 会话编号 */
    @ApiModelProperty(name = "会话编号")
    private String tokenId;

    /** 用户名称 */
    @ApiModelProperty(name = "用户名称")
    private String userName;

    /** 登录IP地址 */
    @ApiModelProperty(name = "登录IP地址")
    private String ipaddr;

    /** 登录地址 */
    @ApiModelProperty(name = "登录地址")
    private String loginLocation;

    /** 浏览器类型 */
    @ApiModelProperty(name = "浏览器类型")
    private String browser;

    /** 操作系统 */
    @ApiModelProperty(name = "操作系统")
    private String os;

    /** 登录时间 */
    @ApiModelProperty(name = "登录时间")
    private Long loginTime;
}
