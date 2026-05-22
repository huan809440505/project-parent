package com.hyl.rock.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 路由显示信息
 */
@Data
@Schema(name = "路由显示信息")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterVo {

    /**
     * 路由名字
     */
    @Schema(name = "路由名字")
    private String name;

    /**
     * 路由地址
     */
    @Schema(name = "路由地址")
    private String path;

    /**
     * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
     */
    @Schema(name = "是否隐藏路由")
    private boolean hidden;

    /**
     * 重定向地址，当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
     */
    @Schema(name = "重定向地址")
    private String redirect;

    /**
     * 组件地址
     */
    @Schema(name = "组件地址")
    private String component;

    /**
     * 路由参数：如 {"id": 1, "name": "ry"}
     */
    @Schema(name = "路由参数")
    private String query;

    /**
     * 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
     */
    @Schema(name = "是否总是显示根菜单")
    private Boolean alwaysShow;

    /**
     * 其他元素
     */
    @Schema(name = "其他元素")
    private MetaVo meta;

    /**
     * 子路由
     */
    @Schema(name = "子路由")
    private List<RouterVo> children;
}
