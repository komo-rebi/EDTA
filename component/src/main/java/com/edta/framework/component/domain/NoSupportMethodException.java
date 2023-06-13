package com.edta.framework.component.domain;

import com.edta.framework.component.exception.SysException;

/**
 * @author wangluyao
 * @date 2022/4/18 16:30
 * @description
 */
public class NoSupportMethodException extends SysException {

    public NoSupportMethodException() {
        super("", "No support method");
    }
}
