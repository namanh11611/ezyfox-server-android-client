package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.EzyMessage;
import com.tvd12.ezyfoxserver.client.codec.EzyObjectToMessage;
import com.tvd12.test.assertion.AssertApplier;
import com.tvd12.test.assertion.AssertSupplier;
import com.tvd12.test.assertion.Asserts;

import org.junit.Test;

public class EzyObjectToMessageTest {

    @Test
    public void test() {
        final A sut = new A();
        Asserts.assertThat(
            new AssertSupplier<Object>() {
                @Override
                public Object apply () {
                    return sut.convertToMessageContent(null);
                }
            }
        )
        .willThrows(UnsupportedOperationException.class);
        Asserts.assertThat(
            new AssertSupplier<Object>() {
                @Override
                public Object apply () {
                    return sut.packToMessage(null, false);
                }
            }
        )
        .willThrows(UnsupportedOperationException.class);
    }

    private static class A implements EzyObjectToMessage {

        @Override
        public EzyMessage convert(Object object) {
            return null;
        }

        @Override
        public byte[] convertToMessageContent (Object object) {
            throw new UnsupportedOperationException("unsupported");
        }

        @Override
        public EzyMessage packToMessage (byte[] content, boolean encrypted) {
            throw new UnsupportedOperationException("unsupported");
        }
    }
}
