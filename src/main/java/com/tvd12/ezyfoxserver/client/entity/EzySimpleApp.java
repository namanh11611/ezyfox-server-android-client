package com.tvd12.ezyfoxserver.client.entity;

import com.tvd12.ezyfoxserver.client.EzyClient;
import com.tvd12.ezyfoxserver.client.builder.EzyArrayBuilder;
import com.tvd12.ezyfoxserver.client.constant.EzyCommand;
import com.tvd12.ezyfoxserver.client.factory.EzyEntityFactory;
import com.tvd12.ezyfoxserver.client.handler.EzyAppDataHandler;
import com.tvd12.ezyfoxserver.client.handler.EzyAppDataHandlers;
import com.tvd12.ezyfoxserver.client.request.EzyRequest;

public class EzySimpleApp extends EzyEntity implements EzyApp {
    protected final int id;
    protected final String name;
    protected final EzyZone zone;
    protected final EzyClient client;
    protected final EzyAppDataHandlers dataHandlers;

    public EzySimpleApp (EzyZone zone, int id, String name) {
        this.client = zone.getClient();
        this.zone = zone;
        this.id = id;
        this.name = name;
        this.dataHandlers = client.getHandlerManager().getAppDataHandlers(name);
    }

    @Override
    public void send (EzyRequest request) {
        String cmd = (String) request.getCommand();
        EzyData data = request.serialize();
        send(cmd, data);
    }

    @Override
    public void send (String cmd) {
        send(cmd, EzyEntityFactory.EMPTY_OBJECT);
    }

    @Override
    public void send (String cmd, EzyData data) {
        send(cmd, data, false);
    }

    @Override
    public void send (String cmd, EzyData data, boolean encrypted) {
        EzyArray commandData = EzyEntityFactory.newArray();
        commandData.add(cmd, data);
        send(commandData, encrypted);
    }

    private void send (EzyArray request, boolean encrypted) {
        EzyArray requestData = EzyEntityFactory.newArray();
        requestData.add(id, request);
        client.send(EzyCommand.APP_REQUEST, requestData, encrypted);
    }

    public int getId () {
        return id;
    }

    public String getName () {
        return name;
    }

    public EzyClient getClient () {
        return client;
    }

    public EzyZone getZone () {
        return zone;
    }

    @SuppressWarnings("rawtypes")
    public EzyAppDataHandler getDataHandler (Object cmd) {
        return dataHandlers.getHandler(cmd);
    }

    @Override
    public String toString () {
        return "App(" +
            "id: " + id + ", " +
            "name: " + name +
            ")";
    }
}
