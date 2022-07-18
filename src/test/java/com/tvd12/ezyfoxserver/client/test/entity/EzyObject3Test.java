package com.tvd12.ezyfoxserver.client.test.entity;

import com.tvd12.ezyfoxserver.client.entity.EzyObject;
import com.tvd12.ezyfoxserver.client.test.CommonBaseTest;
import com.tvd12.test.assertion.Asserts;

import org.junit.Test;

public class EzyObject3Test extends CommonBaseTest {

    @Test
    public void test1() {
        EzyObject object = newObjectBuilder()
            .append("1", new Boolean[][]{{true, false}, {false, true}})
            .append("2", new Byte[][]{{1, 2}, {3, 4}})
            .append("3", new Character[][]{{'a', 'b'}, {'c', 'd'}})
            .append("4", new Double[][]{{1D, 2D}, {3D, 4D}})
            .append("5", new Float[][]{{1F, 2F}, {3F, 4F}})
            .append("6", new Integer[][]{{1, 2}, {3, 4}})
            .append("7", new Long[][]{{1L, 2L}, {3L, 4L}})
            .append("8", new Short[][]{{1, 2}, {3, 4}})
            .append("9", new String[][]{{"1", "2"}, {"3", "4"}})
            .build();
        Asserts.assertEquals((Object) object.get("1", Boolean[][].class), new Boolean[][]{{true, false}, {false, true}});
        Asserts.assertEquals((Object) object.get("2", Byte[][].class), new Byte[][]{{1, 2}, {3, 4}});
        Asserts.assertEquals((Object) object.get("3", Character[][].class), new Character[][]{{'a', 'b'}, {'c', 'd'}});
        Asserts.assertEquals((Object) object.get("4", Double[][].class), new Double[][]{{1D, 2D}, {3D, 4D}});
        Asserts.assertEquals((Object) object.get("5", Float[][].class), new Float[][]{{1F, 2F}, {3F, 4F}});
        Asserts.assertEquals((Object) object.get("6", Integer[][].class), new Integer[][]{{1, 2}, {3, 4}});
        Asserts.assertEquals((Object) object.get("7", Long[][].class), new Long[][]{{1L, 2L}, {3L, 4L}});
        Asserts.assertEquals((Object) object.get("8", Short[][].class), new Short[][]{{1, 2}, {3, 4}});
        Asserts.assertEquals((Object) object.get("9", String[][].class), new String[][]{{"1", "2"}, {"3", "4"}});
    }
}
