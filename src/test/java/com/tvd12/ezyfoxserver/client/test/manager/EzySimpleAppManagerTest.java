package com.tvd12.ezyfoxserver.client.test.manager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tvd12.ezyfoxserver.client.entity.EzyApp;
import com.tvd12.ezyfoxserver.client.manager.EzySimpleAppManager;
import com.tvd12.test.assertion.Asserts;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EzySimpleAppManagerTest {

    @Test
    public void getApp() {
        // given
        String zoneName = "testZoneName";
        EzySimpleAppManager sut = new EzySimpleAppManager(zoneName);
        EzyApp app = mock(EzyApp.class);
        int appId = new Random().nextInt();
        String appName = "testAppName";
        when(app.getId()).thenReturn(appId);
        when(app.getName()).thenReturn(appName);
        sut.addApp(app);

        // when
        EzyApp actualApp = sut.getApp();
        EzyApp actualAppById = sut.getAppById(appId);
        EzyApp actualAppByName = sut.getAppByName(appName);

        // then
        assert actualApp == app;
        assert actualAppById == app;
        assert actualAppByName == app;
        verify(app, times(1)).getId();
        verify(app, times(1)).getName();
    }

    @Test
    public void removeApp() {
        // given
        String zoneName = "testZoneName";
        EzySimpleAppManager sut = new EzySimpleAppManager(zoneName);
        EzyApp app = mock(EzyApp.class);
        int appId = new Random().nextInt();
        String appName = "testAppName";
        when(app.getId()).thenReturn(appId);
        when(app.getName()).thenReturn(appName);
        sut.addApp(app);

        // when
        EzyApp actualApp = sut.removeApp(appId);

        // then
        assert actualApp == app;
        verify(app, times(1)).getId();
        verify(app, times(2)).getName();
    }

    @Test
    public void removeNotExistedApp() {
        // given
        String zoneName = "testZoneName";
        EzySimpleAppManager sut = new EzySimpleAppManager(zoneName);
        EzyApp app = mock(EzyApp.class);
        int appId = new Random().nextInt();
        String appName = "testAppName";
        when(app.getId()).thenReturn(appId);
        when(app.getName()).thenReturn(appName);
        sut.addApp(app);

        // when
        EzyApp actualApp = sut.removeApp(appId + 1);

        // then
        assert actualApp == null;
        verify(app, times(1)).getId();
        verify(app, times(1)).getName();
    }

    @Test
    public void getAppList() {
        // given
        String zoneName = "testZoneName";
        EzySimpleAppManager sut = new EzySimpleAppManager(zoneName);
        EzyApp app = mock(EzyApp.class);
        int appId = new Random().nextInt();
        String appName = "testAppName";
        when(app.getId()).thenReturn(appId);
        when(app.getName()).thenReturn(appName);
        sut.addApp(app);

        // when
        List<EzyApp> actualApps = sut.getAppList();

        // then
        Asserts.assertEquals(actualApps, Collections.singletonList(app), false);
        verify(app, times(1)).getId();
        verify(app, times(1)).getName();
    }

    @Test
    public void clear() {
        // given
        String zoneName = "testZoneName";
        EzySimpleAppManager sut = new EzySimpleAppManager(zoneName);
        EzyApp app = mock(EzyApp.class);
        int appId = new Random().nextInt();
        String appName = "testAppName";
        when(app.getId()).thenReturn(appId);
        when(app.getName()).thenReturn(appName);
        sut.addApp(app);

        // when
        sut.clear();

        // then
        assert sut.getAppList().isEmpty();
        verify(app, times(1)).getId();
        verify(app, times(1)).getName();
    }
}
