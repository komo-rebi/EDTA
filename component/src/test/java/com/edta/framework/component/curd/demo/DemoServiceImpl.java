package com.edta.framework.component.curd.demo;

import com.edta.framework.component.curd.JsonQuery;
import com.edta.framework.component.curd.RetrieveUtil;
import com.edta.framework.component.dto.ModelMap;
import com.edta.framework.component.dto.PageResult;
import org.springframework.stereotype.Service;

/**
 * @author wangluyao
 * @date 2022/7/29 14:14
 * @description
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public PageResult<ModelMap> list(JsonQuery jsonQuery) {
        try {
            return RetrieveUtil.exec("demo", jsonQuery);
        } catch (Throwable e) {
            e.printStackTrace();
            return PageResult.of();
        }
    }
}
