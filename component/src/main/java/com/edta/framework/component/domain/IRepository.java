package com.edta.framework.component.domain;

/**
 * @author wly
 * @date 2021/01/11 10:19:46
 */
public interface IRepository<TEntity extends Entity, TID extends Identity<?>> {

    default TID nextId() {
        throw new NoSupportMethodException();
    }

    default void save(TEntity entity) {
        throw new NoSupportMethodException();
    }

    default void remove(TEntity entity) {
        throw new NoSupportMethodException();
    }

    default void removeById(TID id) {
        throw new NoSupportMethodException();
    }

    default TEntity findById(TID id) {
        throw new NoSupportMethodException();
    }
}

