package com.hyl.rock.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hyl.rock.base.BaseEmptyEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统错误日志 sys_err_log
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "系统错误日志")
@TableName("sys_err_log")
public class SysErrLog extends BaseEmptyEntity {

    /** 日志主键 */
    @Schema(name = "日志主键")
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    /** 日志标题 */
    @Schema(name = "日志标题")
    @TableField("title")
    private String title;

    /** 日志内容 */
    @Schema(name = "日志内容")
    @TableField("content")
    private String content;
}
