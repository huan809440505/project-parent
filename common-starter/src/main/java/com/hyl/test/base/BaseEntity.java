package com.hyl.test.base;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {

    /**
     * 主键Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    @TableField(fill= FieldFill.INSERT)
    private String createBy;
    /**
     * 修改时间
     */
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 修改人
     */
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 是否删除  1：删除  0：正常
     */
    @TableLogic
    private Boolean isDelete;

    /**
     * 备注
     */
    private String remark;
}
