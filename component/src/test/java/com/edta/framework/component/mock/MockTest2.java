package com.edta.framework.component.mock;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.verification.NoInteractionsWanted;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * @author wangluyao
 * @date 2022/7/1 11:50
 * @description
 */
public class MockTest2 {

    @Mock
    private List mockList;

    public MockTest2() {
        // 如果用Mock注解，需要初始化
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shorthand() {
        mockList.add(1);
        verify(mockList).add(1);
    }

    @Test
    public void answerTest() {
        when(mockList.get(anyInt())).thenAnswer(new CustomAnswer());
        assertEquals("hello world:0", mockList.get(0));
        assertEquals("hello world:999", mockList.get(999));
    }

    @Test
    public void answer_with_callback() {
        //使用Answer来生成我们我们期望的返回
        when(mockList.get(anyInt())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return "hello world:" + args[0];
            }
        });
        assertEquals("hello world:0", mockList.get(0));
        assertEquals("hello world:999", mockList.get(999));
    }

    @Test
    public void spy_on_real_objects() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            List list = new LinkedList();
            List spy = spy(list);
            //下面预设的spy.get(0)会报错，因为会调用真实对象的get(0)，所以会抛出越界异常
            //when(spy.get(0)).thenReturn(3);

            //使用doReturn-when可以避免when-thenReturn调用真实对象api
            doReturn(999).when(spy).get(999);
            //预设size()期望值
            when(spy.size()).thenReturn(100);
            //调用真实对象的api
            spy.add(1);
            spy.add(2);
            assertEquals(100, spy.size());
            assertEquals(1, spy.get(0));
            assertEquals(2, spy.get(1));
            verify(spy).add(1);
            verify(spy).add(2);
            assertEquals(999, spy.get(999));
            spy.get(2);
        });

    }

    @Test
    public void real_partial_mock() {
        //通过spy来调用真实的api
        List list = spy(new ArrayList());
        assertEquals(0, list.size());
        A a = mock(A.class);
        //通过thenCallRealMethod来调用真实的api
        when(a.doSomething(anyInt())).thenCallRealMethod();
        assertEquals(999, a.doSomething(999));
    }

    @Test
    public void reset_mock() {
        List list = mock(List.class);
        when(list.size()).thenReturn(10);
        list.add(1);
        assertEquals(10, list.size());
        //重置mock，清除所有的互动和预设
        reset(list);
        assertEquals(0, list.size());
    }

    @Test
    public void verifying_number_of_invocations() {
        List list = mock(List.class);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(3);
        //验证是否被调用一次，等效于下面的times(1)
        verify(list).add(1);
        verify(list, times(1)).add(1);
        //验证是否被调用2次
        verify(list, times(2)).add(2);
        //验证是否被调用3次
        verify(list, times(3)).add(3);
        //验证是否从未被调用过
        verify(list, never()).add(4);
        //验证至少调用一次
        verify(list, atLeastOnce()).add(1);
        //验证至少调用2次
        verify(list, atLeast(2)).add(2);
        //验证至多调用3次
        verify(list, atMost(3)).add(3);
    }

    @Test
    public void consecutive_calls() {
        assertThrows(RuntimeException.class, () -> {
            //模拟连续调用返回期望值，如果分开，则只有最后一个有效
            when(mockList.get(0)).thenReturn(0);
            when(mockList.get(0)).thenReturn(1);
            when(mockList.get(0)).thenReturn(2);
            when(mockList.get(1)).thenReturn(0).thenReturn(1).thenThrow(new RuntimeException());
            assertEquals(2, mockList.get(0));
            assertEquals(2, mockList.get(0));
            assertEquals(0, mockList.get(1));
            assertEquals(1, mockList.get(1));
            //第三次或更多调用都会抛出异常
            mockList.get(1);
        });
    }

    @Test
    public void verification_in_order() {
        List list = mock(List.class);
        List list2 = mock(List.class);
        list.add(1);
        list2.add("hello");
        list.add(2);
        list2.add("world");
        //将需要排序的mock对象放入InOrder
        InOrder inOrder = inOrder(list, list2);
        //下面的代码不能颠倒顺序，验证执行顺序
        inOrder.verify(list).add(1);
        inOrder.verify(list2).add("hello");
        inOrder.verify(list).add(2);
        inOrder.verify(list2).add("world");
    }

    @Test
    public void verify_interaction() {
        List list = mock(List.class);
        List list2 = mock(List.class);
        List list3 = mock(List.class);
        list.add(1);
        verify(list).add(1);
        verify(list, never()).add(2);
        //验证零互动行为
        verifyZeroInteractions(list2, list3);
    }

    @Test
    public void find_redundant_interaction() {
        assertThrows(NoInteractionsWanted.class, () -> {
            List list = mock(List.class);
            list.add(1);
            list.add(2);
            verify(list, times(2)).add(anyInt());
            //检查是否有未被验证的互动行为，因为add(1)和add(2)都会被上面的anyInt()验证到，所以下面的代码会通过
            verifyNoMoreInteractions(list);

            List list2 = mock(List.class);
            list2.add(1);
            list2.add(2);
            verify(list2).add(1);
            //检查是否有未被验证的互动行为，因为add(2)没有被验证，所以下面的代码会失败抛出异常
            verifyNoMoreInteractions(list2);
        });
    }

    private class CustomAnswer implements Answer<String> {
        @Override
        public String answer(InvocationOnMock invocation) throws Throwable {
            Object[] args = invocation.getArguments();
            return "hello world:" + args[0];
        }
    }

    class A {
        public int doSomething(int i) {
            return i;
        }
    }
}
