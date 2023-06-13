package com.edta.framework.component.queryhelper;

/**
 * @author wangluyao
 * @date 2022/6/18 10:28
 * @description
 */
public class PageField implements Page {

    private final int pageIndex;
    private final int pageSize;

    public PageField() {
        this.pageIndex = 0;
        this.pageSize = 10000;
    }

    public PageField(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public PageField of(int pageIndex, int pageSize) {
        return new PageField(pageIndex, pageSize);
    }

    @Override
    public int getPageIndex() {
        return pageIndex;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }
}
