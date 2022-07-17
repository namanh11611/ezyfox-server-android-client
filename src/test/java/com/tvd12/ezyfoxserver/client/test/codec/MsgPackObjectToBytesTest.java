package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.EzyMessageSerializer;
import com.tvd12.ezyfoxserver.client.codec.MsgPackObjectToBytes;
import com.tvd12.test.assertion.AssertApplier;
import com.tvd12.test.assertion.Asserts;
import com.tvd12.test.base.BaseTest;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

public class MsgPackObjectToBytesTest extends BaseTest {

    @Test
    public void test() {
        // given
        EzyMessageSerializer serializer = mock(EzyMessageSerializer.class);
        when(serializer.serialize(any())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer (InvocationOnMock invocation) throws Throwable {
                throw new IllegalArgumentException("just test");
            }
        });

        // when
        final MsgPackObjectToBytes instance = new MsgPackObjectToBytes(serializer);
        Throwable e = Asserts.assertThrows(new AssertApplier() {
            @Override
            public void apply () {
                instance.convert(new Object());
            }
        });

        // then
        Asserts.assertEqualsType(e, IllegalArgumentException.class);
    }
}
