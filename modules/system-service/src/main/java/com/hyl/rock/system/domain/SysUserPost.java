package com.hyl.rock.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户和岗位关联 sys_user_post
 */
@Data
@Schema(name = "用户和岗位关联")
@TableName("sys_user_post")
public class SysUserPost implements Serializable {

    /** 用户ID */
    @Schema(name = "用户ID")
    @TableField("user_id")
    private Long userId;

    /** 岗位ID */
    @Schema(name = "岗位ID")
    @TableField("post_id")
    private Long postId;
}
