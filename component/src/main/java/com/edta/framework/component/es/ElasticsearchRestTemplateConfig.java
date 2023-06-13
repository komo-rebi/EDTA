package com.edta.framework.component.es;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

/**
 * @author wangluyao
 * @date 2022/4/27 13:26
 * @description
 */
@Slf4j
@Component
@ConditionalOnClass(RestHighLevelClient.class)
public class ElasticsearchRestTemplateConfig {

    public ElasticsearchRestTemplateConfig() {
        log.info("Load");
    }

//    public static class ElasticsearchTemplateBean extends AbstractElasticsearchConfiguration {
//
//        @Autowired
//        public ElasticsearchOperations elasticsearchOperations;
//
//        @Override
//        public RestHighLevelClient elasticsearchClient() {
//            return elasticsearchOperations;
//        }

//        private final RestHighLevelClient restHighLevelClient;
//
//        public ElasticsearchTemplateBean(RestHighLevelClient restHighLevelClient) {
//            this.restHighLevelClient = restHighLevelClient;
//        }
//
////        /**
////         * 用于注册转换器
////         * 用默认的转换器导致save数据时驼峰下划线不同的字段映射不上
////         *
////         * @return
////         */
////        @Override
////        @Bean
////        public EntityMapper entityMapper() {
////            ElasticsearchEntityMapper entityMapper = new ElasticsearchEntityMapper(
////                    elasticsearchMappingContext(),
////                    new DefaultConversionService());
////            entityMapper.setConversions(elasticsearchCustomConversions());
////            return entityMapper;
////        }
//
//        @Override
//        public RestHighLevelClient elasticsearchClient() {
//            return restHighLevelClient;
//        }
//    }

}
