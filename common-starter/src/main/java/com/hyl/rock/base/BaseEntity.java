package com.hyl.rock.base;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEntity implements Serializable {

    /**
     * 主键Id
     */
    @ApiModelProperty(name = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "创建时间")
    @TableField(value = "create_time", fill= FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(name = "创建人")
    @TableField(value = "create_by", fill= FieldFill.INSERT)
    private String createBy;

    /**
     * 修改时间
     */
    @ApiModelProperty(name = "修改时间")
    @TableField(value = "update_time", fill= FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    @ApiModelProperty(name = "修改人")
    @TableField(value = "update_by", fill= FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 是否删除（0代表存在 1代表删除）
     */
    @ApiModelProperty(name = "是否删除（0代表存在 1代表删除）")
    @TableField("is_delete")
    @TableLogic
    private Boolean isDelete;

    /**
     * 备注
     */
    @ApiModelProperty(name = "备注")
    @TableField("remark")
    private String remark;
}
