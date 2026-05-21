package com.hyl.rock.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hyl.rock.base.BaseEmptyEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 系统访问记录表 sys_logininfor
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "系统访问记录表")
@TableName("sys_logininfor")
public class SysLogininfor extends BaseEmptyEntity {

    /** ID */
    @ApiModelProperty(name = "序号")
    @TableId(value = "info_id", type = IdType.AUTO)
    private Long infoId;

    /** 用户账号 */
    @ApiModelProperty(name = "用户账号")
    @TableField("user_name")
    private String userName;

    /** 状态 0成功 1失败 */
    @ApiModelProperty(name = "状态 0成功 1失败")
    @TableField("status")
    private String status;

    /** 地址 */
    @ApiModelProperty(name = "地址")
    @TableField("ipaddr")
    private String ipaddr;

    /** 描述 */
    @ApiModelProperty(name = "描述")
    @TableField("msg")
    private String msg;

    /** 访问时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "访问时间")
    @TableField("access_time")
    private Date accessTime;
}
