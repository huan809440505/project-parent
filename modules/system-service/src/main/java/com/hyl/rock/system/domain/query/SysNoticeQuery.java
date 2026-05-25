package com.hyl.rock.system.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "系统访问记录")
public class SysNoticeQuery {

    @Schema(name = "公告标题")
    private String noticeTitle;

    @Schema(name = "公告类型（1通知 2公告）")
    private String noticeType;

    @Schema(name = "创建人")
    private String createBy;

    @Schema(name = "开始日期,格式: yyyy-MM-dd")
    private String startDate;

    @Schema(name = "结束日期,格式: yyyy-MM-dd")
    private String endDate;
}
