package com.hyl.rock.system.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "菜单权限表")
public class SysMenuQuery {

    @Schema(name = "菜单名称")
    private String menuName;

    @Schema(name = "显示状态（0显示 1隐藏）")
    private String visible;

    @Schema(name = "菜单状态（0正常 1停用）")
    private String status;

    @Schema(name = "用户id",hidden = true)
    private Long userId;
}
