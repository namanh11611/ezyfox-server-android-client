package com.tvd12.ezyfoxserver.client.test.entity;

import com.tvd12.ezyfoxserver.client.entity.EzyArray;
import com.tvd12.ezyfoxserver.client.test.CommonBaseTest;

import org.junit.Test;

public class EzyArray2Test extends CommonBaseTest {

    @Test
    public void test() {
        EzyArray array = newArrayBuilder()
            .append((Object) null)
            .append("abc")
            .build();
        assert !array.isEmpty();
        System.out.println(array);
    }
}
