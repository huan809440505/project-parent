package com.hyl.rock.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hyl.rock.base.BaseEmptyEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 参数配置表 sys_config
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "参数配置表")
@TableName("sys_config")
public class SysConfig extends BaseEmptyEntity {

    /** 参数主键 */
    @ApiModelProperty(name = "参数主键")
    @TableId(value = "config_id", type = IdType.AUTO)
    private Long configId;

    /** 参数名称 */
    @ApiModelProperty(name = "参数名称")
    @TableField("config_name")
    private String configName;

    /** 参数键名 */
    @ApiModelProperty(name = "参数键名")
    @TableField("config_key")
    private String configKey;

    /** 参数键值 */
    @ApiModelProperty(name = "参数键值")
    @TableField("config_value")
    private String configValue;

    /** 系统内置（Y是 N否） */
    @ApiModelProperty(name = "系统内置")
    @TableField("config_type")
    private String configType;
}
