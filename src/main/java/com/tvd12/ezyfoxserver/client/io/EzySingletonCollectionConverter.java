package com.tvd12.ezyfoxserver.client.io;

public final class EzySingletonCollectionConverter
    extends EzySimpleCollectionConverter {

    private static final EzySingletonCollectionConverter INSTANCE
        = new EzySingletonCollectionConverter();

    private EzySingletonCollectionConverter () {
        super(EzySingletonOutputTransformer.getInstance());
    }

    public static EzySingletonCollectionConverter getInstance () {
        return INSTANCE;
    }
}
