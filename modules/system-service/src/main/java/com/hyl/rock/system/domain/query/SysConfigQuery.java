package com.hyl.rock.system.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "参数配置表")
public class SysConfigQuery {

    /** 参数名称 */
    @Schema(name = "参数名称")
    private String configName;

    /** 参数键名 */
    @Schema(name = "参数键名")
    private String configKey;

    /** 系统内置（Y是 N否） */
    @Schema(name = "系统内置")
    private String configType;

    @Schema(name = "开始日期,格式: yyyy-MM-dd")
    private String startDate;

    @Schema(name = "结束日期,格式: yyyy-MM-dd")
    private String endDate;
}
