package com.hyl.rock.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文件信息
 */
@Data
@Schema(name = "文件信息")
public class SysFile {

    /**
     * 文件名称
     */
    @Schema(name = "文件名称")
    private String name;

    /**
     * 文件地址
     */
    @Schema(name = "文件地址")
    private String url;
}
