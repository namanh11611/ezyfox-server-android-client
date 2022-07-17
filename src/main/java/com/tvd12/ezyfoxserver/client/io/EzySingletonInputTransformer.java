package com.tvd12.ezyfoxserver.client.io;

public class EzySingletonInputTransformer
    extends EzySimpleInputTransformer {

    private static final EzySingletonInputTransformer INSTANCE
        = new EzySingletonInputTransformer();

    private EzySingletonInputTransformer () {}

    public static EzySingletonInputTransformer getInstance () {
        return INSTANCE;
    }

}
