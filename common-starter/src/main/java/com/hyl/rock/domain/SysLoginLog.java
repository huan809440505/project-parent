package com.hyl.rock.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hyl.rock.base.BaseEmptyEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 系统访问记录表 sys_login_log
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "系统访问记录表")
@TableName("sys_login_log")
public class SysLoginLog extends BaseEmptyEntity {

    /** ID */
    @ExcelProperty(value = "序号")
    @Schema(name = "序号")
    @TableId(value = "info_id", type = IdType.AUTO)
    private Long infoId;

    /** 用户账号 */
    @ExcelProperty(value = "用户账号")
    @Schema(name = "用户账号")
    @TableField("user_name")
    private String userName;

    /** 状态 0成功 1失败 */
    @ExcelProperty(value = "状态 0成功 1失败")
    @Schema(name = "状态 0成功 1失败")
    @TableField("status")
    private String status;

    /** 地址 */
    @ExcelProperty(value = "地址")
    @Schema(name = "地址")
    @TableField("ipaddr")
    private String ipaddr;

    /** 描述 */
    @ExcelProperty(value = "描述")
    @Schema(name = "描述")
    @TableField("msg")
    private String msg;

    /** 访问时间 */
    @ExcelProperty(value = "访问时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(name = "访问时间")
    @TableField("access_time")
    private LocalDateTime accessTime;
}
