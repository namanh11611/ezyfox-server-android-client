package com.tvd12.ezyfoxserver.client.test.entity;

import com.tvd12.ezyfoxserver.client.entity.EzyObject;
import com.tvd12.ezyfoxserver.client.factory.EzyEntityFactory;

import org.junit.Test;

public class EzyRoObjectTest {

    @Test
    public void test() {
        EzyObject object = EzyEntityFactory.newObjectBuilder()
            .append("a", 1)
            .build();
        assert object.get("a", int.class, 0) == (Object) 1;
        assert object.get("aaa", int.class, 0) == (Object) 0;
        assert object.getValue("a", int.class, 0) == (Object) 1;
        assert object.getValue("aaa", int.class, 0) == (Object) 0;
        assert object.size() == 1;
    }
}
