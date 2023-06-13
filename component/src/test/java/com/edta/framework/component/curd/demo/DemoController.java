package com.edta.framework.component.curd.demo;

import com.edta.framework.component.curd.JsonQuery;
import com.edta.framework.component.dto.ModelMap;
import com.edta.framework.component.dto.PageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangluyao
 * @date 2022/7/29 13:58
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/demo/v1/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @PostMapping("list")
    public PageResponse<ModelMap> list(@RequestBody JsonQuery jsonQuery) {
        return PageResponse.buildSuccess(demoService.list(jsonQuery));
    }
}
