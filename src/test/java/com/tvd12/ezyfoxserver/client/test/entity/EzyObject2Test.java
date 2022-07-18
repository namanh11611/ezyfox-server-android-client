package com.tvd12.ezyfoxserver.client.test.entity;

import com.tvd12.ezyfoxserver.client.entity.EzyObject;
import com.tvd12.ezyfoxserver.client.test.CommonBaseTest;
import com.tvd12.test.assertion.Asserts;

import org.junit.Test;

public class EzyObject2Test extends CommonBaseTest {

    @Test
    public void test1() {
        EzyObject object = newObjectBuilder()
            .append("1", new boolean[][]{{true, false}, {false, true}})
            .append("2", new byte[][]{{1, 2}, {3, 4}})
            .append("3", new char[][]{{'a', 'b'}, {'c', 'd'}})
            .append("4", new double[][]{{1, 2}, {3, 4}})
            .append("5", new float[][]{{1, 2}, {3, 4}})
            .append("6", new int[][]{{1, 2}, {3, 4}})
            .append("7", new long[][]{{1, 2}, {3, 4}})
            .append("8", new short[][]{{1, 2}, {3, 4}})
            .append("9", new String[][]{{"1", "2"}, {"3", "4"}})
            .build();
        Asserts.assertEquals((Object) object.get("1", boolean[][].class), new boolean[][]{{true, false}, {false,
            true}});
        Asserts.assertEquals((Object) object.get("2", byte[][].class), new byte[][]{{1, 2}, {3, 4}});
        Asserts.assertEquals((Object) object.get("3", char[][].class), new char[][]{{'a', 'b'}, {'c', 'd'}});
        Asserts.assertEquals((Object) object.get("4", double[][].class), new double[][]{{1, 2}, {3, 4}});
        Asserts.assertEquals((Object) object.get("5", float[][].class), new float[][]{{1, 2}, {3, 4}});
        Asserts.assertEquals((Object) object.get("6", int[][].class), new int[][]{{1, 2}, {3, 4}});
        Asserts.assertEquals((Object) object.get("7", long[][].class), new long[][]{{1, 2}, {3, 4}});
        Asserts.assertEquals((Object) object.get("8", short[][].class), new short[][]{{1, 2}, {3, 4}});
        Asserts.assertEquals((Object) object.get("9", String[][].class), new String[][]{{"1", "2"}, {"3", "4"}});
    }
}
