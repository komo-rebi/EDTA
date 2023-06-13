package com.edta.framework.component.curd;

/**
 * @author wangluyao
 * @date 2022/7/28 17:51
 * @description
 */
@FunctionalInterface
public interface ConvertFunction<Domain, R> {

    R exec(Domain domain);

    class DefaultConvertFunction<Domain, R> implements ConvertFunction<Domain, R> {

        @Override
        public R exec(Domain domain) {
            return (R) domain;
        }
    }
}
