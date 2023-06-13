package com.edta.framework.component.page;

import java.io.Closeable;
import java.util.Iterator;

/**
 * @author wangluyao
 * @date 2023/3/1 15:38
 * @description
 */
public interface PageIterator<T> extends Iterator<T>, Closeable {

    long getTotalSize();
}
