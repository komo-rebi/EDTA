package com.edta.framework.component.util;

import com.edta.framework.component.exception.BadParamException;
import lombok.SneakyThrows;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author wangluyao
 * @date 2022/7/1 23:16
 * @description
 */
public class EnumUtil {

    @SneakyThrows
    public static <E extends Enum<E>, T> E value(Class<E> eClass, T t, Function<E, T> sFunction) {
        E[] es = eClass.getEnumConstants();
        for (E e : es) {
            if (Objects.equals(sFunction.apply(e), t)) {
                return e;
            }
        }
        throw new BadParamException();
    }

    public static void main(String[] args) {
        EnumUtil.value(Test.class, 1, Test::getNum);
    }

    enum Test {
        A(1), B(2), C(3);

        int num;

        Test(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }
    }
}
