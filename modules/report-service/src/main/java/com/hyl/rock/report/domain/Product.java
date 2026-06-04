package com.hyl.rock.report.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 产品实体类
 * @Document 注解用于指定索引名称
 */
@Schema(name = "产品实体类")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "products", createIndex = true)
public class Product {

    @Id
    private String id;

    /**
     * 产品名称 - 支持全文搜索
     * FieldType.Text 类型会被分词
     * fields 定义多字段映射
     */
    @Schema(name = "产品名称")
    @MultiField(
            mainField = @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart"),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword),
                    @InnerField(suffix = "pinyin", type = FieldType.Text, analyzer = "pinyin")
            }
    )
    private String name;

    /**
     * 产品标题 - 简单文本字段
     */
    @Schema(name = "产品标题")
    @Field(type = FieldType.Text, analyzer = "standard")
    private String title;

    /**
     * 产品描述 - 大文本字段
     */
    @Schema(name = "产品描述")
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String description;

    /**
     * 价格 - 浮点型
     */
    @Schema(name = "价格")
    @Field(type = FieldType.Double)
    private BigDecimal price;

    /**
     * 库存 - 整数型
     */
    @Schema(name = "库存")
    @Field(type = FieldType.Integer)
    private Integer stock;

    /**
     * 分类 - 关键字类型，用于精确匹配
     */
    @Schema(name = "分类")
    @Field(type = FieldType.Keyword)
    private String category;

    /**
     * 标签列表 - 数组类型
     */
    @Schema(name = "标签列表")
    @Field(type = FieldType.Keyword)
    private List<String> tags;

    /**
     * 品牌 - 关键字类型
     */
    @Schema(name = "品牌")
    @Field(type = FieldType.Keyword)
    private String brand;

    /**
     * 创建时间 - 日期类型
     */
    @Schema(name = "创建时间")
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime createTime;

    /**
     * 更新时间 - 日期类型
     */
    @Schema(name = "更新时间")
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime updateTime;

    /**
     * 是否上架
     */
    @Schema(name = "是否上架")
    @Field(type = FieldType.Boolean)
    private Boolean isActive;

    /**
     * 评分 - 嵌套对象类型
     */
    @Schema(name = "评分")
    @Field(type = FieldType.Nested)
    private List<Rating> ratings;

    /**
     * 规格参数 - 对象类型
     */
    @Schema(name = "规格参数")
    @Field(type = FieldType.Object)
    private Map<String, Object> specifications;

    /**
     * 地理位置信息
     */
    @Schema(name = "地理位置信息")
    @GeoPointField
    private GeoPoint location;

    /**
     * 评分嵌套类
     */
    @Schema(name = "评分嵌套类")
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Rating {

        @Schema(name = "用户名称")
        @Field(type = FieldType.Text)
        private String username;

        @Schema(name = "分数")
        @Field(type = FieldType.Integer)
        private Integer score;

        @Schema(name = "内容")
        @Field(type = FieldType.Text)
        private String comment;

        @Schema(name = "评分时间")
        @Field(type = FieldType.Date)
        private LocalDateTime ratingTime;
    }
}
