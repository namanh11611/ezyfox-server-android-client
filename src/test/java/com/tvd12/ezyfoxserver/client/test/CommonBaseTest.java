package com.tvd12.ezyfoxserver.client.test;

import com.tvd12.ezyfoxserver.client.builder.EzyArrayBuilder;
import com.tvd12.ezyfoxserver.client.builder.EzyObjectBuilder;
import com.tvd12.ezyfoxserver.client.factory.EzyEntityFactory;
import com.tvd12.test.base.BaseTest;

public class CommonBaseTest extends BaseTest {

    protected EzyObjectBuilder newObjectBuilder() {
        return EzyEntityFactory.create(EzyObjectBuilder.class);
    }

    protected EzyArrayBuilder newArrayBuilder() {
        return EzyEntityFactory.create(EzyArrayBuilder.class);
    }
}
