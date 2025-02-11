package com.tvd12.ezyfoxserver.client.test.entity;

import com.tvd12.ezyfoxserver.client.entity.EzyHashMap;
import com.tvd12.ezyfoxserver.client.entity.EzyObject;
import com.tvd12.ezyfoxserver.client.factory.EzyEntityFactory;
import com.tvd12.test.base.BaseTest;

import org.junit.Test;

public class EzyHashMapTest extends BaseTest {

    @Test
    public void test() {
        EzyHashMap map = new ExEzyHashMap();
        map.put("1", "2");
        assert map.isNotNullValue("1");
        assert !map.isNotNullValue("2");

        EzyObject object = EzyEntityFactory.newObject();
        object.put("a", "b");
        try {
            object.get("a", int.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class ExEzyHashMap extends EzyHashMap {
        private static final long serialVersionUID = -5733872612446105669L;

        public ExEzyHashMap() {
            super(null, null);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <V> V put(Object key, Object value) {
            return (V) map.put(key, value);
        }
    }
}
