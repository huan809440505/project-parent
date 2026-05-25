package com.hyl.rock.system.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "岗位信息表")
public class SysPostQuery {

    @Schema(name = "岗位编码")
    private String postCode;

    @Schema(name = "状态")
    private String status;

    @Schema(name = "岗位名称")
    private String postName;
}
