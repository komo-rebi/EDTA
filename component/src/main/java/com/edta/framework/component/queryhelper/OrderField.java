package com.edta.framework.component.queryhelper;

/**
 * @author wangluyao
 * @date 2022/5/18 15:30
 * @description
 */
public interface OrderField {

    String ASC = "asc";
    String DESC = "desc";

    String getOrder();

    boolean isAsc();
}
