package com.hyl.rock.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文件信息
 */
@Data
@ApiModel(value = "文件信息")
public class SysFile {

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String name;

    /**
     * 文件地址
     */
    @ApiModelProperty(value = "文件地址")
    private String url;
}
