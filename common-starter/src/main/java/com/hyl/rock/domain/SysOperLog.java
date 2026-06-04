package com.hyl.rock.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hyl.rock.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 操作日志记录表 sys_oper_log
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "操作日志")
@TableName("sys_oper_log")
public class SysOperLog extends BaseEntity {

    /** 日志主键 */
    @ExcelProperty(value = "日志主键")
    @Schema(name = "日志主键")
    @TableId(value = "oper_id", type = IdType.AUTO)
    private Long operId;

    /** 操作模块 */
    @ExcelProperty(value = "操作模块")
    @Schema(name = "操作模块")
    @TableField("title")
    private String title;

    /** 业务类型（0其它 1新增 2修改 3删除） */
    @ExcelProperty(value = "业务类型（0其它 1新增 2修改 3删除）")
    @Schema(name = "业务类型（0其它 1新增 2修改 3删除）")
    @TableField("business_type")
    private Integer businessType;

    /** 业务类型数组 */
    @Schema(name = "业务类型数组")
    @TableField(exist = false)
    private Integer[] businessTypes;

    /** 请求方法 */
    @ExcelProperty(value = "请求方法")
    @Schema(name = "请求方法")
    @TableField("method")
    private String method;

    /** 请求方式 */
    @ExcelProperty(value = "请求方式")
    @Schema(name = "请求方式")
    @TableField("request_method")
    private String requestMethod;

    /** 操作类别（0其它 1后台用户 2手机端用户） */
    @ExcelProperty(value = "操作类别（0其它 1后台用户 2手机端用户）")
    @Schema(name = "操作类别（0其它 1后台用户 2手机端用户）")
    @TableField("operator_type")
    private Integer operatorType;

    /** 操作人员 */
    @ExcelProperty(value = "操作人员")
    @Schema(name = "操作人员")
    @TableField("oper_name")
    private String operName;

    /** 部门名称 */
    @ExcelProperty(value = "部门名称")
    @Schema(name = "部门名称")
    @TableField("dept_name")
    private String deptName;

    /** 请求url */
    @ExcelProperty(value = "请求地址")
    @Schema(name = "请求地址")
    @TableField("oper_url")
    private String operUrl;

    /** 操作地址 */
    @ExcelProperty(value = "操作地址")
    @Schema(name = "操作地址")
    @TableField("oper_ip")
    private String operIp;

    /** 请求参数 */
    @ExcelProperty(value = "请求参数")
    @Schema(name = "请求参数")
    @TableField("oper_param")
    private String operParam;

    /** 返回参数 */
    @ExcelProperty(value = "返回参数")
    @Schema(name = "返回参数")
    @TableField("json_result")
    private String jsonResult;

    /** 操作状态（0正常 1异常） */
    @ExcelProperty(value = "操作状态（0正常 1异常）")
    @Schema(name = "状态（0正常 1异常）")
    @TableField("status")
    private Integer status;

    /** 错误消息 */
    @ExcelProperty(value = "错误消息")
    @Schema(name = "错误消息")
    @TableField("error_msg")
    private String errorMsg;

    /** 操作时间 */
    @ExcelProperty(value = "操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(name = "操作时间")
    @TableField("oper_time")
    private LocalDateTime operTime;

    /** 消耗时间 */
    @ExcelProperty(value = "消耗时间")
    @Schema(name = "消耗时间")
    @TableField("cost_time")
    private Long costTime;
}
