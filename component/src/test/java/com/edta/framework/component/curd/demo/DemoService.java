package com.edta.framework.component.curd.demo;

import com.edta.framework.component.curd.JsonQuery;
import com.edta.framework.component.dto.ModelMap;
import com.edta.framework.component.dto.PageResult;

/**
 * @author wangluyao
 * @date 2022/7/29 14:10
 * @description
 */
public interface DemoService {

    PageResult<ModelMap> list(JsonQuery jsonQuery);
}
