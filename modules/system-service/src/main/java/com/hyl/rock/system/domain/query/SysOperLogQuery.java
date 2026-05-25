package com.hyl.rock.system.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "操作日志")
public class SysOperLogQuery {

    @Schema(name = "操作地址")
    private String operIp;

    @Schema(name = "操作模块")
    private String title;

    @Schema(name = "业务类型（0其它 1新增 2修改 3删除）")
    private Integer businessType;

    @Schema(name = "业务类型数组")
    private List<Integer> businessTypes;

    @Schema(name = "状态（0正常 1异常）")
    private Integer status;

    @Schema(name = "操作人员")
    private String operName;

    @Schema(name = "开始日期,格式: yyyy-MM-dd")
    private String startDate;

    @Schema(name = "结束日期,格式: yyyy-MM-dd")
    private String endDate;
}
