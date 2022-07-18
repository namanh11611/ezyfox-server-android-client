package com.tvd12.ezyfoxserver.client.test.entity;

import com.tvd12.ezyfoxserver.client.entity.EzyObject;
import com.tvd12.ezyfoxserver.client.test.CommonBaseTest;
import com.tvd12.test.assertion.Asserts;

import org.junit.Test;

public class EzyObject4Test extends CommonBaseTest {

    @Test
    public void test1() {
        EzyObject object1 = newObjectBuilder().append("1", "a").build();
        EzyObject object2 = newObjectBuilder().append("2", "b").build();
        EzyObject object = newObjectBuilder()
            .append("1", new EzyObject[][]{{object1}, {object2}})
            .build();
        EzyObject[][] itemss = object.get("1", EzyObject[][].class);
        Asserts.assertEquals(itemss[0][0].get("1", String.class), "a");
        Asserts.assertEquals(itemss[1][0].get("2", String.class), "b");
    }
}
