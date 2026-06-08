package com.hyl.rock.gen.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hyl.rock.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "代码生成表")
@TableName("gen_table")
public class GenTable extends BaseEntity {

    /** 编号 */
    @Schema(name = "编号")
    @TableId(value = "table_id", type = IdType.AUTO)
    private Long tableId;

    /** 表名称 */
    @NotBlank(message = "表名称不能为空")
    @Schema(name = "表名称")
    @TableField("table_name")
    private String tableName;

    /** 表描述 */
    @NotBlank(message = "表描述不能为空")
    @Schema(name = "表描述")
    @TableField("table_comment")
    private String tableComment;

    /** 关联父表的表名 */
    @Schema(name = "关联父表的表名")
    @TableField("sub_table_name")
    private String subTableName;

    /** 本表关联父表的外键名 */
    @Schema(name = "本表关联父表的外键名")
    @TableField("sub_table_fk_name")
    private String subTableFkName;

    /** 实体类名称(首字母大写) */
    @NotBlank(message = "实体类名称不能为空")
    @Schema(name = "实体类名称")
    @TableField("class_name")
    private String className;

    /** 使用的模板（crud单表操作 tree树表操作 sub主子表操作） */
    @Schema(name = "使用的模板")
    @TableField("tpl_category")
    private String tplCategory;

    /** 前端类型（element-ui模版 element-plus模版 element-plus-typescript模版） */
    @Schema(name = "前端类型")
    @TableField("tpl_web_type")
    private String tplWebType;

    /** 生成包路径 */
    @NotBlank(message = "生成包路径不能为空")
    @Schema(name = "生成包路径")
    @TableField("package_name")
    private String packageName;

    /** 生成模块名 */
    @NotBlank(message = "生成模块名不能为空")
    @Schema(name = "生成模块名")
    @TableField("module_name")
    private String moduleName;

    /** 生成业务名 */
    @NotBlank(message = "生成业务名不能为空")
    @Schema(name = "生成业务名")
    @TableField("business_name")
    private String businessName;

    /** 生成功能名 */
    @NotBlank(message = "生成功能名不能为空")
    @Schema(name = "生成功能名")
    @TableField("function_name")
    private String functionName;

    /** 生成作者 */
    @NotBlank(message = "作者不能为空")
    @Schema(name = "生成作者")
    @TableField("function_author")
    private String functionAuthor;

    /** 表单布局（单列 双列 三列） */
    @Schema(name = "表单布局")
    @TableField("form_col_num")
    private Integer formColNum;

    /** 生成代码方式（0zip压缩包 1自定义路径） */
    @Schema(name = "生成代码方式")
    @TableField("gen_type")
    private String genType;

    /** 生成路径（不填默认项目路径） */
    @Schema(name = "生成路径")
    @TableField("gen_path")
    private String genPath;

    /** 其它生成选项 */
    @Schema(name = "其它生成选项")
    @TableField("options")
    private String options;

    /** 主键信息 */
    @Schema(name = "主键信息")
    @TableField(exist = false)
    private GenTableColumn pkColumn;

    /** 子表信息 */
    @Schema(name = "子表信息")
    @TableField(exist = false)
    private GenTable subTable;

    /** 表列信息 */
    @Valid
    @Schema(name = "表列信息")
    @TableField(exist = false)
    private List<GenTableColumn> columns;

    /** 树编码字段 */
    @Schema(name = "树编码字段")
    @TableField(exist = false)
    private String treeCode;

    /** 树父编码字段 */
    @Schema(name = "树父编码字段")
    @TableField(exist = false)
    private String treeParentCode;

    /** 树名称字段 */
    @Schema(name = "树名称字段")
    @TableField(exist = false)
    private String treeName;

    /** 上级菜单ID字段 */
    @Schema(name = "上级菜单ID字段")
    @TableField(exist = false)
    private Long parentMenuId;

    /** 上级菜单名称字段 */
    @Schema(name = "上级菜单名称字段")
    @TableField(exist = false)
    private String parentMenuName;

    /** 是否生成详情页 */
    @Schema(name = "是否生成详情页")
    @TableField(exist = false)
    private boolean isView;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;
}
