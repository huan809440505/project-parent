package com.hyl.rock.system.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "字典类型表")
public class SysDictTypeQuery {

    /** 字典名称 */
    @Schema(name = "字典名称")
    private String dictName;

    /** 状态（0正常 1停用） */
    @Schema(name = "状态（0正常 1停用）")
    private String status;

    @Schema(name = "字典类型")
    private String dictType;

    @Schema(name = "开始日期,格式: yyyy-MM-dd")
    private String startDate;

    @Schema(name = "结束日期,格式: yyyy-MM-dd")
    private String endDate;
}
