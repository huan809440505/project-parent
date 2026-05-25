package com.hyl.rock.system.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "系统访问记录")
public class SysLoginLogQuery {

    /** 用户账号 */
    @Schema(name = "用户账号")
    private String userName;

    /** 状态 0成功 1失败 */
    @Schema(name = "状态 0成功 1失败")
    private String status;

    /** 地址 */
    @Schema(name = "地址")
    private String ipaddr;

    @Schema(name = "开始日期,格式: yyyy-MM-dd")
    private String startDate;

    @Schema(name = "结束日期,格式: yyyy-MM-dd")
    private String endDate;
}
