package com.hyl.rock.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.hyl.rock.base.BaseEmptyEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典数据表 sys_dict_data
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "字典数据表")
@TableName("sys_dict_data")
public class SysDictData extends BaseEmptyEntity {

    /** 字典编码 */
    @ApiModelProperty(name = "字典编码")
    @TableId(value = "dict_code", type = IdType.AUTO)
    private Long dictCode;

    /** 字典排序 */
    @ApiModelProperty(name = "字典排序")
    @TableField("dict_sort")
    private Long dictSort;

    /** 字典标签 */
    @NotBlank(message = "字典标签不能为空")
    @Size(min = 0, max = 100, message = "字典标签长度不能超过100个字符")
    @ApiModelProperty(name = "字典标签")
    @TableField("dict_label")
    private String dictLabel;

    /** 字典键值 */
    @NotBlank(message = "字典键值不能为空")
    @Size(min = 0, max = 100, message = "字典键值长度不能超过100个字符")
    @ApiModelProperty(name = "字典键值")
    @TableField("dict_value")
    private String dictValue;

    /** 字典类型 */
    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型长度不能超过100个字符")
    @ApiModelProperty(name = "字典类型")
    @TableField("dict_type")
    private String dictType;

    /** 样式属性（其他样式扩展） */
    @Size(min = 0, max = 100, message = "样式属性长度不能超过100个字符")
    @ApiModelProperty(name = "样式属性")
    @TableField("css_class")
    private String cssClass;

    /** 表格字典样式 */
    @ApiModelProperty(name = "表格字典样式")
    @TableField("list_class")
    private String listClass;

    /** 是否默认（Y是 N否） */
    @ApiModelProperty(name = "是否默认（Y是 N否）")
    @TableField("is_default")
    private String isDefault;

    /** 状态（0正常 1停用） */
    @ApiModelProperty(name = "状态（0正常 1停用）")
    @TableField("status")
    private String status;
}
