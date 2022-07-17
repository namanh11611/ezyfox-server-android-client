package com.tvd12.ezyfoxserver.client.test.io;

import static com.tvd12.ezyfoxserver.client.io.EzyNumbersConverter.numbersToPrimitiveBytes;
import static com.tvd12.ezyfoxserver.client.io.EzyNumbersConverter.numbersToPrimitiveChars;
import static com.tvd12.ezyfoxserver.client.io.EzyNumbersConverter.numbersToPrimitiveDoubles;
import static com.tvd12.ezyfoxserver.client.io.EzyNumbersConverter.numbersToPrimitiveFloats;
import static com.tvd12.ezyfoxserver.client.io.EzyNumbersConverter.numbersToPrimitiveInts;
import static com.tvd12.ezyfoxserver.client.io.EzyNumbersConverter.numbersToPrimitiveLongs;
import static com.tvd12.ezyfoxserver.client.io.EzyNumbersConverter.numbersToPrimitiveShorts;
import static com.tvd12.ezyfoxserver.client.io.EzyNumbersConverter.numbersToWrapperBytes;
import static com.tvd12.ezyfoxserver.client.io.EzyNumbersConverter.numbersToWrapperChars;
import static com.tvd12.ezyfoxserver.client.io.EzyNumbersConverter.numbersToWrapperDoubles;
import static com.tvd12.ezyfoxserver.client.io.EzyNumbersConverter.numbersToWrapperFloats;
import static com.tvd12.ezyfoxserver.client.io.EzyNumbersConverter.numbersToWrapperInts;
import static com.tvd12.ezyfoxserver.client.io.EzyNumbersConverter.numbersToWrapperLongs;
import static com.tvd12.ezyfoxserver.client.io.EzyNumbersConverter.numbersToWrapperNumbers;
import static com.tvd12.ezyfoxserver.client.io.EzyNumbersConverter.numbersToWrapperShorts;
import static com.tvd12.ezyfoxserver.client.io.EzyNumbersConverter.objectToChar;
import static com.tvd12.ezyfoxserver.client.io.EzyNumbersConverter.objectsToWrapperNumbers;

import com.tvd12.ezyfoxserver.client.function.EzyFunction;
import com.tvd12.ezyfoxserver.client.function.EzyNewArray;
import com.tvd12.ezyfoxserver.client.function.EzyNumber;
import com.tvd12.ezyfoxserver.client.io.EzyNumbersConverter;
import com.tvd12.test.assertion.Asserts;
import com.tvd12.test.base.BaseTest;

import org.junit.Test;

import java.util.Arrays;

public class EzyNumbersConverterTest extends BaseTest {

    @Override
    public Class<?> getTestClass() {
        return EzyNumbersConverter.class;
    }

    @Test
    public void test() {
        Asserts.assertEquals(numbersToPrimitiveBytes(Arrays.asList(1, 2, 3)),
            new byte[]{1, 2, 3});
        Asserts.assertEquals(numbersToPrimitiveChars(Arrays.asList(1, 2, 3)),
            new char[]{1, 2, 3});
        Asserts.assertEquals(numbersToPrimitiveDoubles(Arrays.asList(1, 2, 3)),
            new double[]{1, 2, 3});
        Asserts.assertEquals(numbersToPrimitiveFloats(Arrays.asList(1, 2, 3)),
            new float[]{1, 2, 3});
        Asserts.assertEquals(numbersToPrimitiveInts(Arrays.asList(1, 2, 3)),
            new int[]{1, 2, 3});
        Asserts.assertEquals(numbersToPrimitiveLongs(Arrays.asList(1, 2, 3)),
            new long[]{1, 2, 3});
        Asserts.assertEquals(numbersToPrimitiveShorts(Arrays.asList(1, 2, 3)),
            new short[]{1, 2, 3});

        Asserts.assertEquals(
            objectsToWrapperNumbers(
                Arrays.asList("1", "2", "3"),
                new EzyNewArray<Long>() {
                    @Override
                    public Long[] apply (int size) {
                        return new Long[size];
                    }
                },
                new EzyFunction<String, Long>() {
                    @Override
                    public Long apply (String number) {
                        return Long.valueOf(number);
                    }
                }
            ), new Long[]{1L, 2L, 3L});

        Asserts.assertEquals(
            numbersToWrapperNumbers(
                Arrays.<Number>asList(1, 2, 3),
                new EzyNewArray<Number>() {
                    @Override
                    public Number[] apply (int size) {
                        return new Number[size];
                    }
                },
                new EzyNumber<Number>() {
                    @Override
                    public Number apply (Number number) {
                        return number.longValue();
                    }
                }),
            new Number[]{1L, 2L, 3L});

        Asserts.assertEquals(numbersToWrapperBytes(Arrays.<Number>asList(1, 2, 3)),
            new Byte[]{1, 2, 3});
        Asserts.assertEquals(numbersToWrapperChars(Arrays.asList(1, 2, 3)),
            new Character[]{1, 2, 3});
        Asserts.assertEquals(numbersToWrapperDoubles(Arrays.<Number>asList(1, 2, 3)),
            new Double[]{1D, 2D, 3D});
        Asserts.assertEquals(numbersToWrapperFloats(Arrays.<Number>asList(1, 2, 3)),
            new Float[]{1F, 2F, 3F});
        Asserts.assertEquals(numbersToWrapperInts(Arrays.<Number>asList(1, 2, 3)),
            new Integer[]{1, 2, 3});
        Asserts.assertEquals(numbersToWrapperLongs(Arrays.<Number>asList(1, 2, 3)),
            new Long[]{1L, 2L, 3L});
        Asserts.assertEquals(numbersToWrapperShorts(Arrays.<Number>asList(1, 2, 3)),
            new Short[]{1, 2, 3});
        Asserts.assertEquals(objectToChar('a'), 'a');
    }

    @Test
    public void convertNumberTest() {
        Byte value = EzyNumbersConverter.convertNumber((Object) 100L, new EzyNumber<Byte>() {
            @Override
            public Byte apply (Number number) {
                return number.byteValue();
            }
        });
        System.err.println("value = " + value);
        assert value == 100;
    }
}
