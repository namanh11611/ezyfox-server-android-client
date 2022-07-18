package com.tvd12.ezyfoxserver.client.test.entity;

import com.tvd12.ezyfoxserver.client.builder.EzyObjectBuilder;
import com.tvd12.ezyfoxserver.client.entity.EzyHashMap;
import com.tvd12.ezyfoxserver.client.entity.EzyObject;
import com.tvd12.ezyfoxserver.client.factory.EzyEntityFactory;
import com.tvd12.test.assertion.AssertApplier;
import com.tvd12.test.assertion.Asserts;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EzyObject1Test extends EzyEntityTest {

    @Test
    public void test1() {
        Map<String, Object> map = new HashMap<>();
        EzyObjectBuilder firstBuilder
            = newObjectBuilder().append("1'", "a'");
        map.put("1", "a");
        EzyObject object = newObjectBuilder()
            .append(map)
            .append("2", firstBuilder)
            .append("3", "c")
            .append("4", "d")
            .append("5", "e")
            .build();
        Asserts.assertEquals(object.get("1"), "a");
        Asserts.assertEquals(object.getWithDefault("1", "b"), "a");
        Asserts.assertEquals(object.get("3", String.class), "c");
        Asserts.assertEquals(object.remove("4"), "d");
        Asserts.assertEquals(object.size(), 4);
        Asserts.assertFalse(object.isEmpty());
        Asserts.assertTrue(object.keySet().containsAll(Arrays.asList("1", "2", "3", "5")));
        object.entrySet();
        object.toMap();
        String str = object.toString();
        System.out.println(str);
        EzyObject clone = object.duplicate();
        Asserts.assertEquals(object, clone);
        Asserts.assertTrue(clone.keySet().containsAll(Arrays.asList("1", "2", "3", "5")));
        object.clear();
        Asserts.assertEquals(object.getWithDefault("1", "b"), "b");

        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        object = newObjectBuilder()
            .append("uuid1", uuid1)
            .append("uuid2", uuid2.toString())
            .append("bigInteger1", new BigInteger("1000"))
            .append("bigInteger2", new BigInteger("1001").toString())
            .append("bigDecimal1", new BigDecimal("2000.2"))
            .append("bigDecimal2", new BigDecimal("2000.3").toString())
            .build();
        assert object.get("uuid1", UUID.class).equals(uuid1);
        assert object.get("uuid2", UUID.class).equals(uuid2);
        assert object.get("bigInteger1", BigInteger.class).equals(new BigInteger("1000"));
        assert object.get("bigInteger2", BigInteger.class).equals(new BigInteger("1001"));
        assert object.get("bigDecimal1", BigDecimal.class).equals(new BigDecimal("2000.2"));
        assert object.get("bigDecimal2", BigDecimal.class).equals(new BigDecimal("2000.3"));
    }

    @Test
    public void test2() {
        final EzyObject object = new EzyHashMap(null, null) {
            private static final long serialVersionUID = -4366815253239566713L;

            @Override
            public Object clone() throws CloneNotSupportedException {
                throw new CloneNotSupportedException();
            }
        };
        Throwable e = Asserts.assertThrows(new AssertApplier() {
            @Override
            public void apply () {
                object.duplicate();
            }
        });
        Asserts.assertEqualsType(e, IllegalStateException.class);
    }

    @SuppressWarnings({"ConstantConditions", "EqualsWithItself"})
    @Test
    public void equalsAndHashCodeTest() {
        EzyObject a = EzyEntityFactory.newObjectBuilder()
            .append("a", 1)
            .append("b", 2)
            .build();
        assert !a.equals(null);
        assert a.equals(a);
        assert !a.equals(new Object());
        EzyObject b = EzyEntityFactory.newObjectBuilder()
            .append("a", 1)
            .append("b", 2)
            .build();
        Asserts.assertEquals(a, b);
        EzyObject c = EzyEntityFactory.newObjectBuilder()
            .append("a", 1)
            .build();
        assert !a.equals(c);
    }

    @Test
    public void compareToTest() {
        EzyEntityFactory.newObjectBuilder()
            .append("a", 1)
            .append("b", 2)
            .build();
        EzyEntityFactory.newObjectBuilder()
            .append("a", 1)
            .append("b", 2)
            .build();
        EzyEntityFactory.newObjectBuilder()
            .append("a", 1)
            .build();
        EzyEntityFactory.newObjectBuilder()
            .append("a", 1)
            .append("b", 1)
            .build();
        EzyEntityFactory.newObjectBuilder()
            .append("a", 3)
            .append("b", 1)
            .build();
        EzyEntityFactory.newObjectBuilder()
            .append("a", (Object) null)
            .append("b", 1)
            .build();
        EzyEntityFactory.newObjectBuilder()
            .append("a", (Object) null)
            .append("b", 1)
            .build();
        EzyEntityFactory.newObjectBuilder()
            .append("a", new Object())
            .append("b", 1)
            .build();

        EzyEntityFactory.newObjectBuilder()
            .append("a", EzyEntityFactory.newObjectBuilder()
                .append("hello", "world"))
            .append("b", EzyEntityFactory.newArrayBuilder()
                .append(1, 2, 3))
            .build();
        EzyEntityFactory.newObjectBuilder()
            .append("a", EzyEntityFactory.newObjectBuilder()
                .append("hello", "world"))
            .append("b", EzyEntityFactory.newArrayBuilder()
                .append(1, 2, 3))
            .build();
    }
}
