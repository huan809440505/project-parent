package com.hyl.rock.system.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "错误日志查询")
public class SysErrLogQuery {

    @Schema(name = "日志标题")
    private String title;

    @Schema(name = "开始时间,格式:yyyy-MM-dd HH:mm:ss")
    private String startTime;

    @Schema(name = "结束时间,格式:yyyy-MM-dd HH:mm:ss")
    private String endTime;
}
