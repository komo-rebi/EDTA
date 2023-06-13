package com.edta.framework.component.queryhelper;

import lombok.AllArgsConstructor;

/**
 * @author wangluyao
 * @date 2022/5/18 15:47
 * @description
 */
@AllArgsConstructor
public class AbstractOrderField implements OrderField {

    private String order;
    private Boolean isAsc;

    public AbstractOrderField(String order) {
        this.order = order;
        this.isAsc = false;
    }

    @Override
    public String getOrder() {
        return order;
    }

    @Override
    public boolean isAsc() {
        return Boolean.TRUE.equals(isAsc);
    }
}
