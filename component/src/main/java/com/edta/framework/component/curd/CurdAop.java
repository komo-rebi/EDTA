package com.edta.framework.component.curd;

/**
 * @author wangluyao
 * @date 2022/7/29 15:08
 * @description
 */
public interface CurdAop {

    @FunctionalInterface
    interface Before<Input> {
        void exec(Input input);
    }

    @FunctionalInterface
    interface After<Input> {
        void exec(Input input);
    }
//
//    @FunctionalInterface
//    interface Around<Input, OutPut> {
//        OutPut exec(Input input);
//    }

    @FunctionalInterface
    interface AfterThrowing<Input> {
        void exec(Input input, Throwable throwable);
    }

    @FunctionalInterface
    interface AfterReturning<Input> {
        void exec(Input input);
    }

    class DefaultBefore<Input> implements Before<Input> {
        @Override
        public void exec(Input input) {

        }
    }

    class DefaultAfter<Input> implements After<Input> {
        @Override
        public void exec(Input input) {

        }
    }

    class DefaultAfterThrowing<Input> implements AfterThrowing<Input> {

        @Override
        public void exec(Input input, Throwable throwable) {

        }
    }

    class DefaultAfterReturning<Input> implements AfterReturning<Input> {
        @Override
        public void exec(Input input) {

        }
    }
}
