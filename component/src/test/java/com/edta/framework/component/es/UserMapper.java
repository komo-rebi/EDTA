package com.edta.framework.component.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author wangluyao
 * @date 2022/7/9 10:42
 * @description
 */
@Component
public interface UserMapper extends ElasticsearchRepository<UserDO, String> {
}
