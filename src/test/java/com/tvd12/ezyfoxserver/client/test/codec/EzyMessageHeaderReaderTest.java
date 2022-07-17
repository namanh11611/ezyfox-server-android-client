package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.EzyMessageHeaderReader;
import com.tvd12.test.base.BaseTest;

import org.junit.Test;

public class EzyMessageHeaderReaderTest extends BaseTest {

    @Test
    public void test() {
        assert EzyMessageHeaderReader.readEncrypted((byte) ((1 << 1) & 0xFF));
        assert EzyMessageHeaderReader.readCompressed((byte) ((1 << 2) & 0xFF));
        assert EzyMessageHeaderReader.readText((byte) ((1 << 3) & 0xFF));
        assert EzyMessageHeaderReader.readHasNext((byte) ((1 << 7) & 0xFF));
        assert !EzyMessageHeaderReader.readHasNext((byte) ((1 << 6) & 0xFF));
    }

    @Override
    public Class<?> getTestClass() {
        return EzyMessageHeaderReader.class;
    }
}
