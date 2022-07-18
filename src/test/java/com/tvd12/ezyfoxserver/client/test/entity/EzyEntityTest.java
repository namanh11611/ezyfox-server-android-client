package com.tvd12.ezyfoxserver.client.test.entity;

import com.tvd12.ezyfoxserver.client.entity.EzyEntity;
import com.tvd12.ezyfoxserver.client.test.CommonBaseTest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class EzyEntityTest extends CommonBaseTest {

    @Test
    public void test() {
        EzyEntity entity = new EzyEntity() {};
        Map<String, String> strs = new HashMap<>();
        strs.put("1", "a");
        entity.setProperties(strs);
        assert entity.getProperty("1").equals("a");
        assert entity.getProperty("1", String.class).equals("a");
        assert entity.containsKey("1");
        assert entity.getProperties().containsKey("1");
        entity.removeProperty("1");
        assert entity.getProperty("1") == null;
        entity.setProperty(EzyEntityTest.class, this);
        assert entity.getProperty(EzyEntityTest.class) == this;
    }
}
