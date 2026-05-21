package com.hyl.rock.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户和岗位关联 sys_user_post
 */
@Data
@ApiModel(value = "用户和岗位关联")
@TableName("sys_user_post")
public class SysUserPost implements Serializable {

    /** 用户ID */
    @ApiModelProperty(name = "用户ID")
    @TableField("user_id")
    private Long userId;

    /** 岗位ID */
    @ApiModelProperty(name = "岗位ID")
    @TableField("post_id")
    private Long postId;
}
