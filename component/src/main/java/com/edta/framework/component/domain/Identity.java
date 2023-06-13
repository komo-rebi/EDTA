package com.edta.framework.component.domain;

import com.edta.framework.component.exception.Assert;
import com.edta.framework.component.exception.BadParamException;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 唯一标识
 *
 * @author wly
 * @date 2021/01/11 10:12:43
 */
@Getter
@Accessors
@ToString
public abstract class Identity<T> extends ValueObject<Identity<T>> {

    private final T id;

    public Identity(T id) {
        if (id instanceof String) {
            Assert.notBlank((String) id);
        }
        if (id == null) {
            throw new BadParamException("identity cannot be empty");
        }
        this.id = id;
    }

    public T id() {
        return id;
    }

    @Override
    public boolean equals(Identity<T> o) {
        if (this == o) {
            return true;
        }
        if (o != null) {
            return o.id().equals(this.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
