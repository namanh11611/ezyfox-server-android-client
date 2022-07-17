package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.EzyAbstractToBytesSerializer;
import com.tvd12.ezyfoxserver.client.function.EzyParser;
import com.tvd12.test.assertion.AssertApplier;
import com.tvd12.test.assertion.Asserts;
import com.tvd12.test.base.BaseTest;

import org.junit.Test;

import java.util.Map;

public class EzyAbstractSerializerTest extends BaseTest {

    @Test
    public void test() {
        EzyAbstractToBytesSerializer serializer = new Serializer();
        assert serializer.serialize(null) == null;
        assert serializer.serialize("abc") != null;
    }

    @Test
    public void test1() {
        // given
        final EzyAbstractToBytesSerializer serializer = new Serializer();

        // when
        Throwable e = Asserts.assertThrows(new AssertApplier() {
            @Override
            public void apply () {
                serializer.serialize(new EzyAbstractSerializerTest());
            }
        });

        // then
        Asserts.assertEqualsType(e, IllegalArgumentException.class);
    }

    @Test
    public void test2() {
        EzyAbstractToBytesSerializer serializer = new Serializer1();
        serializer.serialize(new EzyAbstractSerializerTest());
    }

    public static class Serializer extends EzyAbstractToBytesSerializer {

        @Override
        protected byte[] parseNil() {
            return null;
        }

        @Override
        protected void addParsers(Map<Class<?>, EzyParser<Object, byte[]>> parsers) {
            parsers.put(String.class, new EzyParser<Object, byte[]>() {
                @Override
                public byte[] parse (Object input) {
                    return input.toString().getBytes();
                }
            });
        }
    }

    public static class Serializer1 extends EzyAbstractToBytesSerializer {

        @Override
        protected byte[] parseNil() {
            return null;
        }

        @Override
        protected byte[] parseWithNoParser(Object value) {
            return new byte[]{};
        }

        @Override
        protected void addParsers(Map<Class<?>, EzyParser<Object, byte[]>> parsers) {
            parsers.put(String.class, new EzyParser<Object, byte[]>() {
                @Override
                public byte[] parse (Object input) {
                    return input.toString().getBytes();
                }
            });
        }
    }
}
