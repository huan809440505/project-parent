package com.hyl.rock.base;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEmptyEntity implements Serializable {

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(name = "创建人")
    @TableField("create_by")
    private String createBy;

    /**
     * 修改时间
     */
    @ApiModelProperty(name = "修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    @ApiModelProperty(name = "修改人")
    @TableField("update_by")
    private String updateBy;

    /**
     * 备注
     */
    @ApiModelProperty(name = "备注")
    @TableField("remark")
    private String remark;
}
