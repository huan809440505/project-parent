package com.hyl.rock.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hyl.rock.base.BaseEmptyEntity;
import com.hyl.rock.xss.Xss;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通知公告表 sys_notice
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "通知公告表")
@TableName("sys_notice")
public class SysNotice extends BaseEmptyEntity {

    /** 公告ID */
    @ApiModelProperty(name = "公告ID")
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Long noticeId;

    /** 公告标题 */
    @Xss(message = "公告标题不能包含脚本字符")
    @NotBlank(message = "公告标题不能为空")
    @Size(min = 0, max = 50, message = "公告标题不能超过50个字符")
    @ApiModelProperty(name = "公告标题")
    @TableField("notice_title")
    private String noticeTitle;

    /** 公告类型（1通知 2公告） */
    @ApiModelProperty(name = "公告类型（1通知 2公告）")
    @TableField("notice_type")
    private String noticeType;

    /** 公告内容 */
    @ApiModelProperty(name = "公告内容")
    @TableField("notice_content")
    private String noticeContent;

    /** 公告状态（0正常 1关闭） */
    @ApiModelProperty(name = "公告状态（0正常 1关闭）")
    @TableField("status")
    private String status;

    /** 是否已读 */
    @JsonProperty("isRead")
    @ApiModelProperty(name = "是否已读")
    @TableField("is_read")
    private boolean isRead;

    public boolean getIsRead() {
        return isRead;
    }
}
