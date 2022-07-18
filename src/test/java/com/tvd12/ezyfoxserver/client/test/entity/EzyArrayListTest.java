package com.tvd12.ezyfoxserver.client.test.entity;

import com.tvd12.ezyfoxserver.client.entity.EzyArray;
import com.tvd12.ezyfoxserver.client.entity.EzyArrayList;
import com.tvd12.ezyfoxserver.client.factory.EzyEntityFactory;
import com.tvd12.ezyfoxserver.client.io.EzyCollectionConverter;
import com.tvd12.ezyfoxserver.client.io.EzyInputTransformer;
import com.tvd12.ezyfoxserver.client.io.EzyOutputTransformer;
import com.tvd12.test.base.BaseTest;

import org.junit.Test;

public class EzyArrayListTest extends BaseTest {

    @Test
    public void test() {
        EzyArrayList arr = new EzyEzyArrayList(null, null, null);
        arr.add((Object) null);
        arr.add("a");
        assert arr.isNotNullValue(1);
        assert !arr.isNotNullValue(0);
        assert !arr.isNotNullValue(100);
        EzyArray newArray = EzyEntityFactory.newArray();
        newArray.add(3);
        newArray.add(1);
        newArray.add(2);
        assert newArray.get(1, int.class) == 1;
        assert newArray.get(2, int.class) == 2;

        EzyArray myArray = EzyEntityFactory.newArray();
        myArray.add("a");
        try {
            myArray.get(0, int.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class EzyEzyArrayList extends EzyArrayList {
        private static final long serialVersionUID = 1L;

        public EzyEzyArrayList(
            EzyInputTransformer inputTransformer,
            EzyOutputTransformer outputTransformer,
            EzyCollectionConverter collectionConverter
        ) {
            super(inputTransformer, outputTransformer, collectionConverter);
        }

        @Override
        public void add(Object item) {
            list.add(item);
        }
    }
}
