package com.tvd12.ezyfoxserver.client.test.entity;

import com.tvd12.ezyfoxserver.client.entity.EzyArray;
import com.tvd12.ezyfoxserver.client.factory.EzyEntityFactory;

import org.junit.Test;

import java.util.Arrays;

public class EzyRoArrayTest {

    @Test
    public void test() {
        EzyArray array = EzyEntityFactory.newArrayBuilder()
            .append(1)
            .append(2)
            .build();
        assert array.getWithDefault(0, 0) == 1;
        assert array.getWithDefault(3, 0) == 0;
        assert array.get(1, int.class, 0) == 2;
        assert array.get(3, int.class, 0) == 0;
        assert array.getValue(0, int.class, 0) == (Object) 1;
        assert array.getValue(3, int.class, 0) == (Object) 0;
        array.add(Arrays.asList(2, 3));
    }
}
