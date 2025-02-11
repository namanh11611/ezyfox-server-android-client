package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.EzyMessage;
import com.tvd12.ezyfoxserver.client.codec.EzyMessageHeader;

import org.junit.Test;

public class EzyMessageTest {

    @Test
    public void test() {
        EzyMessage message = new ExEzyMessage(true);
        assert message.getSizeLength() == 4;
        EzyMessage message2 = new ExEzyMessage(false);
        assert message2.getSizeLength() == 2;
    }

    public static class ExEzyMessage implements EzyMessage {

        protected final boolean hasBigSize;

        public ExEzyMessage(boolean h) {
            this.hasBigSize = h;
        }

        @Override
        public int getSize() {
            return 0;
        }

        @Override
        public byte[] getContent() {
            return null;
        }

        @Override
        public EzyMessageHeader getHeader() {
            return null;
        }

        @Override
        public int getByteCount() {
            return 0;
        }

        @Override
        public boolean hasBigSize() {
            return hasBigSize;
        }

        @Override
        public int getContentStartIndex() {
            return 0;
        }

        @Override
        public int getSizeLength () {
            return hasBigSize() ? 4 : 2;
        }
    }
}
