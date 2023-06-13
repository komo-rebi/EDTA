package com.edta.framework.component.queryhelper;

import java.util.Collections;
import java.util.List;

/**
 * @author wangluyao
 * @date 2022/6/18 10:44
 * @description
 */
public class SourceField {

    private final List<String> includes;
    private final List<String> excludes;

    public SourceField() {
        this(Collections.emptyList(), Collections.emptyList());
    }

    public SourceField(List<String> includes) {
        this(includes, Collections.emptyList());
    }

    public SourceField(List<String> includes, List<String> excludes) {
        this.includes = includes;
        this.excludes = excludes;
    }

    public List<String> getIncludes() {
        return includes;
    }

    public List<String> getExcludes() {
        return excludes;
    }
}
