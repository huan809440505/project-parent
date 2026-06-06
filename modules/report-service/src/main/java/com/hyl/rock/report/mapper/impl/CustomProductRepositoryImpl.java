package com.hyl.rock.report.mapper.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Highlight;
import co.elastic.clients.elasticsearch.core.search.HighlightField;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.SourceConfig;
import com.hyl.rock.report.domain.Product;
import com.hyl.rock.report.mapper.CustomProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomProductRepositoryImpl implements CustomProductRepository {

    private final ElasticsearchClient elasticsearchClient;

    @Override
    public Page<Product> complexSearch(String keyword, List<String> categories, BigDecimal minPrice, BigDecimal maxPrice, List<String> brands, Pageable pageable) {

        try {
            // 构建布尔查询
            BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();

            // 关键字查询
            if (keyword != null && !keyword.trim().isEmpty()) {
                boolQueryBuilder.should(
                        Query.of(q -> q
                                .multiMatch(m -> m
                                        .query(keyword)
                                        .fields("name^3", "title^2", "description")
                                        .type(TextQueryType.BestFields)
                                )
                        )
                );
            }

            // 分类过滤
            if (categories != null && !categories.isEmpty()) {
                boolQueryBuilder.filter(
                        Query.of(q -> q
                                .terms(t -> t
                                        .field("category")
                                        .terms(t2 -> t2.value(categories.stream()
                                                .map(FieldValue::of)
                                                .toList()))
                                )
                        )
                );
            }

            // 价格范围过滤
            if (minPrice != null && maxPrice != null) {

                RangeQuery rangeQuery = RangeQuery.of(r -> r.number(n ->
                        n.field("price")
                                .gte(minPrice.doubleValue())
                                .lte(maxPrice.doubleValue())
                ));

                boolQueryBuilder.filter(
                        Query.of(q->q.range(rangeQuery))
                );

            }

            // 品牌过滤
            if (brands != null && !brands.isEmpty()) {
                boolQueryBuilder.filter(
                        Query.of(q -> q
                                .terms(t -> t
                                        .field("brand")
                                        .terms(t2 -> t2.value(brands.stream()
                                                .map(FieldValue::of)
                                                .toList()))
                                )
                        )
                );
            }

            // 只查询上架商品
            boolQueryBuilder.filter(
                    Query.of(q -> q
                            .term(t -> t
                                    .field("isActive")
                                    .value(true)
                            )
                    )
            );

            // 执行搜索
            SearchResponse<Product> response = elasticsearchClient.search(
                    s -> s
                            .index("products")
                            .query(q -> q.bool(boolQueryBuilder.build()))
                            .from((int) pageable.getOffset())
                            .size(pageable.getPageSize())
                            .source(SourceConfig.of(sc -> sc
                                    .filter(f -> f
                                            .excludes("specifications") // 排除大字段
                                    )
                            ))
                            .sort(so -> so
                                    .field(f -> f
                                            .field("_score")
                                            .order(SortOrder.Desc)
                                    )
                            )
                            .sort(so -> so
                                    .field(f -> f
                                            .field("createTime")
                                            .order(SortOrder.Desc)
                                    )
                            ),
                    Product.class
            );

            // 转换结果
            List<Product> products = response.hits().hits().stream()
                    .map(Hit::source)
                    .filter(Objects::nonNull)
                    .toList();

            long total = response.hits().total() != null ?
                    response.hits().total().value() : 0;

            return new PageImpl<>(products, pageable, total);

        } catch (IOException e) {
            log.error("复杂搜索失败", e);
            throw new RuntimeException("搜索失败", e);
        }
    }

    @Override
    public Page<Product> searchWithHighlight(String keyword, Pageable pageable) {
        try {
            // 构建高亮查询
            Highlight.Builder highlightBuilder = new Highlight.Builder();
            highlightBuilder.fields("name", HighlightField.of(hf->hf.preTags("<em>").postTags("</em>")));

            SearchResponse<Product> response = elasticsearchClient.search(s -> s
                            .index("products")
                            .query(q -> q
                                    .multiMatch(m -> m
                                            .query(keyword)
                                            .fields("name", "description")
                                    )
                            )
                            .highlight(highlightBuilder.build())
                            .from((int) pageable.getOffset())
                            .size(pageable.getPageSize()),
                    Product.class
            );

            // 处理高亮结果
            List<Product> products = response.hits().hits().stream()
                    .map(hit -> {
                        Product product = hit.source();
                        if (product != null && hit.highlight() != null) {
                            // 处理高亮字段（这里简化处理）
                            Map<String, List<String>> highlights = hit.highlight();
                            // 可以将高亮结果设置到临时字段中
                        }
                        return product;
                    })
                    .filter(Objects::nonNull)
                    .toList();

            long total = response.hits().total() != null ?
                    response.hits().total().value() : 0;

            return new PageImpl<>(products, pageable, total);

        } catch (IOException e) {
            log.error("高亮搜索失败", e);
            throw new RuntimeException("搜索失败", e);
        }
    }
}
