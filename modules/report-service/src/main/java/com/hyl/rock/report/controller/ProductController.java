package com.hyl.rock.report.controller;

import com.hyl.rock.base.Result;
import com.hyl.rock.report.domain.Product;
import com.hyl.rock.report.service.ProductService;
import com.hyl.rock.web.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Tag(name = "商品基础功能控制器")
@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController extends BaseController {

    @Resource
    private ProductService productService;

    // ============ CRUD 接口 ============

    /**
     * 创建产品
     */
    @Operation(summary = "创建产品")
    @PostMapping
    public Result<Product> createProduct(@RequestBody Product product) {
        log.info("创建产品: {}", product.getName());
        Product savedProduct = productService.save(product);
        return success(savedProduct);
    }

    /**
     * 批量创建产品
     */
    @Operation(summary = "批量创建产品")
    @PostMapping("/batch")
    public Result<List<Product>> batchCreateProducts(@RequestBody List<Product> products) {
        log.info("批量创建产品，数量: {}", products.size());
        List<Product> savedProducts = productService.saveAll(products);
        return success(savedProducts);
    }

    /**
     * 根据ID获取产品
     */
    @Operation(summary = "根据ID获取产品")
    @GetMapping("/{id}")
    public Result<Product> getProductById(@PathVariable String id) {
        log.info("获取产品，ID: {}", id);
        Product product = productService.findById(id);
        return success(product);
    }

    /**
     * 获取所有产品
     */
    @Operation(summary = "获取所有产品")
    @GetMapping
    public Result<List<Product>> getAllProducts() {
        log.info("获取所有产品");
        List<Product> products = productService.findAll();
        return success(products);
    }

    /**
     * 分页获取产品
     */
    @Operation(summary = "分页获取产品")
    @GetMapping("/page")
    public Result<Page<Product>> getProductsByPage(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "createTime") String sortBy,
                                                   @RequestParam(defaultValue = "desc") String direction) {

        log.info("分页获取产品，第 {} 页，每页 {} 条", page, size);

        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction)
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<Product> products = productService.findAll(pageable);
        return success(products);
    }

    /**
     * 更新产品
     */
    @Operation(summary = "更新产品")
    @PutMapping("/{id}")
    public Result<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        log.info("更新产品，ID: {}", id);
        // 确保ID一致
        product.setId(id);
        Product updatedProduct = productService.save(product);
        return success(updatedProduct);
    }

    /**
     * 删除产品
     */
    @Operation(summary = "删除产品")
    @DeleteMapping("/{id}")
    public Result<String> deleteProduct(@PathVariable String id) {
        log.info("删除产品，ID: {}", id);
        productService.deleteById(id);
        return toAjax(true);
    }

    // ============ 搜索接口 ============

    /**
     * 根据名称搜索
     */
    @Operation(summary = "根据名称搜索")
    @GetMapping("/search/name")
    public Result<List<Product>> searchByName(@RequestParam String name) {

        log.info("根据名称搜索: {}", name);
        List<Product> products = productService.searchByName(name);
        return success(products);
    }

    /**
     * 根据分类查询
     */
    @Operation(summary = "根据分类查询")
    @GetMapping("/search/category")
    public Result<List<Product>> searchByCategory(@RequestParam String category) {

        log.info("根据分类查询: {}", category);
        List<Product> products = productService.findByCategory(category);
        return success(products);
    }

    /**
     * 根据价格范围查询
     */
    @Operation(summary = "根据价格范围查询")
    @GetMapping("/search/price-range")
    public Result<List<Product>> searchByPriceRange(@RequestParam BigDecimal minPrice,
                                                    @RequestParam BigDecimal maxPrice) {

        log.info("根据价格范围查询: {} - {}", minPrice, maxPrice);
        List<Product> products = productService.findByPriceRange(minPrice, maxPrice);
        return success(products);
    }

    /**
     * 根据品牌查询
     */
    @Operation(summary = "根据品牌查询")
    @GetMapping("/search/brand")
    public Result<List<Product>> searchByBrand(@RequestParam String brand) {

        log.info("根据品牌查询: {}", brand);
        List<Product> products = productService.findByBrand(brand);
        return success(products);
    }

    /**
     * 根据标签查询
     */
    @Operation(summary = "根据标签查询")
    @GetMapping("/search/tag")
    public Result<List<Product>> searchByTag(@RequestParam String tag) {

        log.info("根据标签查询: {}", tag);
        List<Product> products = productService.findByTag(tag);
        return success(products);
    }

    /**
     * 查询上架商品
     */
    @Operation(summary = "查询上架商品")
    @GetMapping("/active")
    public Result<List<Product>> getActiveProducts() {
        log.info("查询上架商品");
        List<Product> products = productService.findActiveProducts();
        return success(products);
    }

    /**
     * 复杂条件搜索
     */
    @Operation(summary = "复杂条件搜索")
    @GetMapping("/search/complex")
    public Result<Page<Product>> complexSearch(@RequestParam(required = false) String keyword,
                                               @RequestParam(required = false) List<String> categories,
                                               @RequestParam(required = false) BigDecimal minPrice,
                                               @RequestParam(required = false) BigDecimal maxPrice,
                                               @RequestParam(required = false) List<String> brands,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {

        log.info("复杂条件搜索，关键字: {}", keyword);

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productService.complexSearch(keyword, categories, minPrice, maxPrice, brands, pageable);

        return success(products);
    }

    /**
     * 全文搜索
     */
    @Operation(summary = "全文搜索")
    @GetMapping("/search/full-text")
    public Result<Page<Product>> fullTextSearch(@RequestParam String keyword,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {

        log.info("全文搜索，关键字: {}", keyword);

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productService.fullTextSearch(keyword, pageable);

        return success(products);
    }

    // ============ 聚合统计接口 ============

    /**
     * 按分类统计
     */
    @Operation(summary = "按分类统计")
    @GetMapping("/stats/category")
    public Result<Map<String, Long>> statsByCategory() {
        log.info("按分类统计");
        Map<String, Long> stats = productService.countByCategory();
        return success(stats);
    }

    /**
     * 按品牌统计
     */
    @Operation(summary = "按品牌统计")
    @GetMapping("/stats/brand")
    public Result<Map<String, Long>> statsByBrand() {
        log.info("按品牌统计");
        Map<String, Long> stats = productService.countByBrand();
        return success(stats);
    }

    /**
     * 价格分布统计
     */
    @Operation(summary = "价格分布统计")
    @GetMapping("/stats/price-distribution")
    public Result<Map<String, Long>> priceDistribution() {
        log.info("价格分布统计");
        Map<String, Long> distribution = productService.priceDistribution();
        return success(distribution);
    }

    // ============ 批量操作接口 ============

    /**
     * 批量更新库存
     */
    @Operation(summary = "批量更新库存")
    @PostMapping("/bulk/stock")
    public Result<String> bulkUpdateStock(@RequestBody Map<String, Integer> stockUpdates) {

        log.info("批量更新库存，更新数量: {}", stockUpdates.size());
        productService.bulkUpdateStock(stockUpdates);
        return toAjax(true);
    }

    /**
     * 批量更新价格
     */
    @Operation(summary = "批量更新价格")
    @PostMapping("/bulk/price")
    public Result<String> bulkUpdatePrice(@RequestBody Map<String, BigDecimal> priceUpdates) {

        log.info("批量更新价格，更新数量: {}", priceUpdates.size());
        productService.bulkUpdatePrice(priceUpdates);
        return toAjax(true);
    }

}
