package com.tvd12.ezyfoxserver.client.config;

import com.tvd12.ezyfoxserver.client.builder.EzyBuilder;

public class EzyPingConfig {

    private final long pingPeriod;
    private final int maxLostPingCount;

    public EzyPingConfig (Builder builder) {
        this.pingPeriod = builder.pingPeriod;
        this.maxLostPingCount = builder.maxLostPingCount;
    }

    public long getPingPeriod () {
        return pingPeriod;
    }

    public int getMaxLostPingCount () {
        return maxLostPingCount;
    }

    public static class Builder implements EzyBuilder<EzyPingConfig> {

        private long pingPeriod = 3000L;
        private int maxLostPingCount = 5;
        private final EzyClientConfig.Builder parent;

        public Builder (EzyClientConfig.Builder parent) {
            this.parent = parent;
        }

        public Builder pingPeriod (long pingPeriod) {
            this.pingPeriod = pingPeriod;
            return this;
        }

        public Builder maxLostPingCount (int maxLostPingCount) {
            this.maxLostPingCount = maxLostPingCount;
            return this;
        }

        public EzyClientConfig.Builder done () {
            return this.parent;
        }

        @Override
        public EzyPingConfig build () {
            return new EzyPingConfig(this);
        }
    }
}
