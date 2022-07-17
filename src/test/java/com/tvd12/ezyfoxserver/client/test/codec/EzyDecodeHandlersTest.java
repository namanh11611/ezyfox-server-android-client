package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.EzyDecodeHandler;
import com.tvd12.ezyfoxserver.client.codec.EzyDecodeHandlers;
import com.tvd12.ezyfoxserver.client.codec.EzyIDecodeState;

import org.junit.Test;

import java.util.Map;

public class EzyDecodeHandlersTest {

    @Test
    public void test() {
        ExEzyDecodeHandlers.builder()
            .build();
    }

    public static class ExEzyDecodeHandlers extends EzyDecodeHandlers {

        public ExEzyDecodeHandlers(Builder builder) {
            super(builder);
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder extends EzyDecodeHandlers.Builder {

            @Override
            protected void addHandlers(Map<EzyIDecodeState, EzyDecodeHandler> answer) {
            }

            @Override
            public EzyDecodeHandlers build() {
                return new ExEzyDecodeHandlers(this);
            }
        }
    }
}
