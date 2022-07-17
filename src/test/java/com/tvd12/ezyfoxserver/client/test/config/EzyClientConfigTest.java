package com.tvd12.ezyfoxserver.client.test.config;

import com.tvd12.ezyfoxserver.client.config.EzyClientConfig;
import com.tvd12.ezyfoxserver.client.config.EzyPingConfig;
import com.tvd12.test.assertion.Asserts;
import com.tvd12.test.util.RandomUtil;

import org.junit.Test;

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
}
