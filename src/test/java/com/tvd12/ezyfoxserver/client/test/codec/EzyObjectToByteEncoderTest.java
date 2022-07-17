package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.EzyObjectToByteEncoder;
import com.tvd12.test.assertion.AssertApplier;
import com.tvd12.test.assertion.AssertSupplier;
import com.tvd12.test.assertion.Asserts;

import org.junit.Test;

public class EzyObjectToByteEncoderTest {

    @Test
    public void test() {
        final EzyObjectToByteEncoder sut = new A();
        Asserts.assertThat(new AssertSupplier<Object>() {
            @Override
            public Object apply () throws Throwable {
                return sut.toMessageContent(null);
            }
        })
        .willThrows(UnsupportedOperationException.class);
        Asserts.assertThat(new AssertSupplier<Object>() {
            @Override
            public Object apply () throws Throwable {
                return sut.encryptMessageContent(null, null);
            }
        })
        .willThrows(UnsupportedOperationException.class);
    }

    private static class A implements EzyObjectToByteEncoder {

        @Override
        public byte[] encode(Object msg) {
            return null;
        }

        @Override
        public byte[] toMessageContent (Object data) {
            throw new UnsupportedOperationException("unsupported");
        }

        @Override
        public byte[] encryptMessageContent (
            byte[] messageContent,
            byte[] encryptionKey
        ) {
            throw new UnsupportedOperationException("unsupported");
        }
    }
}
