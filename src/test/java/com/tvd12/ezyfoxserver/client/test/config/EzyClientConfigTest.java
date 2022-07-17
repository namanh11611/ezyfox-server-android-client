package com.tvd12.ezyfoxserver.client.test.config;

import com.tvd12.ezyfoxserver.client.config.EzyClientConfig;
import com.tvd12.ezyfoxserver.client.config.EzyPingConfig;
import com.tvd12.test.assertion.Asserts;
import com.tvd12.test.util.RandomUtil;

import org.junit.Test;

import java.util.Random;

public class EzyClientConfigTest {

    @Test
    public void propertiesTest() {
        // given
        String zoneName = RandomUtil.randomShortAlphabetString();
        EzyClientConfig clientConfig = EzyClientConfig.builder()
            .zoneName(zoneName)
            .enableDebug()
            .enableSSL()
            .pingConfigBuilder()
            .done()
            .build();

        // when
        EzyPingConfig actualPingConfig = clientConfig.getPing();

        // then
        Asserts.assertNotNull(actualPingConfig);
        Asserts.assertEquals(clientConfig.getZoneName(), zoneName);
        Asserts.assertTrue(clientConfig.isEnableDebug());
        Asserts.assertTrue(clientConfig.isEnableSSL());
    }

    @Test
    public void test() {
        // given
        String zoneName = "testZone";
        String clientName = "testClient";
        int maxReconnectCount = new Random().nextInt();
        int reconnectPeriod = new Random().nextInt();

        EzyClientConfig config = EzyClientConfig.builder()
            .zoneName(zoneName)
            .clientName(clientName)
            .reconnectConfigBuilder()
            .enable(true)
            .maxReconnectCount(maxReconnectCount)
            .reconnectPeriod(reconnectPeriod)
            .done()
            .build();

        // when
        // then
        assert config.getClientName().equals(clientName);
        assert config.getZoneName().equals(zoneName);
        assert config.getReconnect().isEnable();
        assert config.getReconnect().getMaxReconnectCount() == maxReconnectCount;
        assert config.getReconnect().getReconnectPeriod() == reconnectPeriod;
    }

    @Test
    public void testClientNameNull() {
        // given
        String zoneName = "testZone";

        EzyClientConfig config = EzyClientConfig.builder()
            .zoneName(zoneName)
            .build();

        // when
        // then
        assert config.getClientName().equals(zoneName);
    }
}
