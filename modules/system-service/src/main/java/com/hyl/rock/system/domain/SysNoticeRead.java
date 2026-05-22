package com.hyl.rock.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通知公告已读表 sys_notice_read
 */
@Data
@Schema(name = "通知公告已读表")
@TableName("sys_notice_read")
public class SysNoticeRead implements Serializable {

    /** 主键 */
    @Schema(name = "主键")
    @TableId(value = "read_id", type = IdType.AUTO)
    private Long readId;

    /** 公告ID */
    @Schema(name = "公告ID")
    @TableField("notice_id")
    private Long noticeId;

    /** 用户ID */
    @Schema(name = "用户ID")
    @TableField("user_id")
    private Long userId;

    /** 阅读时间 */
    @Schema(name = "阅读时间")
    @TableField("read_time")
    private LocalDateTime readTime;
}
