package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.builder.EzyArrayBuilder;
import com.tvd12.ezyfoxserver.client.builder.EzyObjectBuilder;
import com.tvd12.ezyfoxserver.client.factory.EzyEntityFactory;
import com.tvd12.test.base.BaseTest;

public class CodecBaseTest extends BaseTest {

    public EzyArrayBuilder newArrayBuilder() {
        return EzyEntityFactory.create(EzyArrayBuilder.class);
    }

    public EzyObjectBuilder newObjectBuilder() {
        return EzyEntityFactory.create(EzyObjectBuilder.class);
    }
}
