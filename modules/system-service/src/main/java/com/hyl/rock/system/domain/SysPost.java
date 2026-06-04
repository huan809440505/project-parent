package com.hyl.rock.system.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hyl.rock.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 岗位信息表 sys_post
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "岗位信息表")
@TableName("sys_post")
public class SysPost extends BaseEntity {

    /** 岗位序号 */
    @ExcelProperty(value = "岗位序号")
    @Schema(name = "岗位序号")
    @TableId(value = "post_id", type = IdType.AUTO)
    private Long postId;

    /** 岗位编码 */
    @ExcelProperty(value = "岗位编码")
    @NotBlank(message = "岗位编码不能为空")
    @Size(min = 0, max = 64, message = "岗位编码长度不能超过64个字符")
    @Schema(name = "岗位编码")
    @TableField("post_code")
    private String postCode;

    /** 岗位名称 */
    @ExcelProperty(value = "岗位名称")
    @NotBlank(message = "岗位名称不能为空")
    @Size(min = 0, max = 50, message = "岗位名称长度不能超过50个字符")
    @Schema(name = "岗位名称")
    @TableField("post_name")
    private String postName;

    /** 岗位排序 */
    @ExcelProperty(value = "岗位排序")
    @NotNull(message = "岗位排序不能为空")
    @Schema(name = "岗位排序")
    @TableField("post_sort")
    private Integer postSort;

    /** 状态（0正常 1停用） */
    @ExcelProperty(value = "状态（0正常 1停用）")
    @Schema(name = "状态")
    @TableField("status")
    private String status;

    /** 用户是否存在此岗位标识 默认不存在 */
    @Schema(name = "用户是否存在此岗位标识")
    @TableField(exist = false)
    private boolean flag = false;
}
