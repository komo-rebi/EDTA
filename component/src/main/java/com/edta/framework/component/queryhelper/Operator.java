package com.edta.framework.component.queryhelper;

import java.util.Collection;

/**
 * @author wangluyao
 * @date 2023/2/25 12:56
 * @description
 */
public abstract class Operator<T extends DefaultQuery> {

    private final T t;

    public Operator(T t) {
        this.t = t;
    }

    protected abstract String field();

    public <R> T in(Collection<R> value) {
        this.t.add(new InQueryField<>(field(), value));
        return t;
    }

    public <R> DefaultQuery eq(R value) {
        this.t.add(new EqQueryField<>(field(), value));
        return t;
    }

    public <R> DefaultQuery ne(R value) {
        this.t.add(new NeQueryField<>(field(), value));
        return t;
    }
}
