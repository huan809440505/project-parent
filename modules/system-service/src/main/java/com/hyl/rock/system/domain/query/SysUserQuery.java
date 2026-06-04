package com.hyl.rock.system.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "用户对象")
public class SysUserQuery {

    @Schema(name = "用户ID")
    private Long userId;

    @Schema(name = "登录名称")
    private String userName;

    @Schema(name = "账号状态（0正常 1停用）")
    private String status;

    @Schema(name = "手机号码")
    private String phoneNumber;

    @Schema(name = "开始日期,格式: yyyy-MM-dd")
    private String startDate;

    @Schema(name = "结束日期,格式: yyyy-MM-dd")
    private String endDate;
}
