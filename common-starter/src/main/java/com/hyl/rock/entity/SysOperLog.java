package com.hyl.rock.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hyl.rock.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@ApiModel(value = "操作日志")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysOperLog extends BaseEntity {

    /** 操作模块 */
    @ApiModelProperty(name = "操作模块")
    private String title;

    /** 业务类型（0其它 1新增 2修改 3删除） */
    @ApiModelProperty(name = "业务类型")
    private Integer businessType;

    /** 业务类型数组 */
    private Integer[] businessTypes;

    /** 请求方法 */
    @ApiModelProperty(name = "请求方法")
    private String method;

    /** 请求方式 */
    @ApiModelProperty(name = "请求方式")
    private String requestMethod;

    /** 操作类别（0其它 1后台用户 2手机端用户） */
    @ApiModelProperty(name = "操作类别")
    private Integer operatorType;

    /** 操作人员 */
    @ApiModelProperty(name = "操作人员")
    private String operName;

    /** 部门名称 */
    @ApiModelProperty(name = "部门名称")
    private String deptName;

    /** 请求url */
    @ApiModelProperty(name = "请求地址")
    private String operUrl;

    /** 操作地址 */
    @ApiModelProperty(name = "操作地址")
    private String operIp;

    /** 请求参数 */
    @ApiModelProperty(name = "请求参数")
    private String operParam;

    /** 返回参数 */
    @ApiModelProperty(name = "返回参数")
    private String jsonResult;

    /** 操作状态（0正常 1异常） */
    @ApiModelProperty(name = "状态")
    private Integer status;

    /** 错误消息 */
    @ApiModelProperty(name = "错误消息")
    private String errorMsg;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "操作时间")
    private Date operTime;

    /** 消耗时间 */
    @ApiModelProperty(name = "消耗时间")
    private Long costTime;
}
