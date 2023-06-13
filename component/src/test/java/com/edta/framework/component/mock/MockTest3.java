package com.edta.framework.component.mock;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * @author wangluyao
 * @date 2022/7/1 11:50
 * @description // FIXME 本例没有验证通过
 */
@RunWith(MockitoJUnitRunner.class)
public class MockTest3 {

    @Mock
    private List mockList;

    @Test
    public void shorthand() {
        mockList.add(1);
        verify(mockList).add(1);
    }
}
