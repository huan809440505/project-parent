package com.hyl.rock.report.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.hyl.rock.report.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class IndexInitializer {

    private final ElasticsearchOperations elasticsearchOperations;
    private final ElasticsearchClient elasticsearchClient;

    /**
     * 应用启动时初始化索引
     */
    @Bean
    public CommandLineRunner initIndex() {
        return args -> {
            try {
                // 检查并创建产品索引
                IndexOperations indexOps = elasticsearchOperations.indexOps(Product.class);

                if (!indexOps.exists()) {
                    log.info("创建产品索引...");
                    indexOps.create();

                    // 创建映射
                    indexOps.putMapping(indexOps.createMapping(Product.class));

                    log.info("产品索引创建完成");
                } else {
                    log.info("产品索引已存在");
                }


            } catch (Exception e) {
                log.error("初始化索引失败", e);
            }
        };
    }

}
