package com.tvd12.ezyfoxserver.client.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ThreadLocalRandom;

public class EzyKeysGenerator {

    protected int keySize;
    protected String algorithm;

    public static final String DEFAULT_ALGORITHM = "RSA";

    protected EzyKeysGenerator (Builder<?> builder) {
        this.keySize = builder.keySize;
        this.algorithm = builder.algorithm;
    }

    public KeyPair generate () {
        return generate(newKeyPairGenerator());
    }

    protected KeyPair generate (KeyPairGenerator generator) {
        generator.initialize(keySize);
        return generator.generateKeyPair();
    }

    protected KeyPairGenerator newKeyPairGenerator () {
        try {
            return KeyPairGenerator.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static byte[] randomKey (int keySize) {
        byte[] key = new byte[keySize];
        ThreadLocalRandom.current().nextBytes(key);
        return key;
    }

    @SuppressWarnings("rawtypes")
    public static Builder builder () {
        return new Builder<>();
    }

    @SuppressWarnings("unchecked")
    public static class Builder<B extends Builder<B>> {
        protected int keySize = 2048;
        protected String algorithm = DEFAULT_ALGORITHM;

        public B keySize (int keySize) {
            this.keySize = keySize;
            return (B) this;
        }

        public B algorithm (String algorithm) {
            this.algorithm = algorithm;
            return (B) this;
        }

        public EzyKeysGenerator build () {
            return new EzyKeysGenerator(this);
        }
    }
}
