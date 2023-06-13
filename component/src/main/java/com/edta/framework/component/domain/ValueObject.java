package com.edta.framework.component.domain;

/**
 * 值对象
 *
 * @author wly
 * @date 2021/01/11 10:09:44
 */
public abstract class ValueObject<T extends ValueObject<T>> {

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(T obj) {
        throw new NoSupportMethodException();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ValueObject) {
            return equals((T) object);
        }
        return false;
    }
}
