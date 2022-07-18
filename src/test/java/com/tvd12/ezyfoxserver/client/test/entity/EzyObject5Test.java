package com.tvd12.ezyfoxserver.client.test.entity;

import com.tvd12.ezyfoxserver.client.entity.EzyObject;
import com.tvd12.ezyfoxserver.client.factory.EzyEntityFactory;
import com.tvd12.ezyfoxserver.client.test.CommonBaseTest;
import com.tvd12.test.assertion.Asserts;

import org.junit.Test;

public class EzyObject5Test extends CommonBaseTest {

    @Test
    public void test1() {
        EzyObject object = EzyEntityFactory.newObjectBuilder()
            .append("1", ABC.A)
            .build();
        Asserts.assertEquals(object.get("1", ABC.class), ABC.A);
    }

    public enum ABC {
        A
    }
}
