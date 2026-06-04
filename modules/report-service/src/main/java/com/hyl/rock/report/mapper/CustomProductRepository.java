package com.hyl.rock.report.mapper;

import com.hyl.rock.report.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

/**
 * 自定义Repository接口
 */
public interface CustomProductRepository {

    /**
     * 复杂搜索：多条件组合查询
     */
    Page<Product> complexSearch(String keyword,
                                List<String> categories,
                                BigDecimal minPrice,
                                BigDecimal maxPrice,
                                List<String> brands,
                                Pageable pageable);

    /**
     * 全文搜索并高亮显示
     */
    Page<Product> searchWithHighlight(String keyword, Pageable pageable);


}
