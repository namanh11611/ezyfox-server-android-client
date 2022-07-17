package com.tvd12.ezyfoxserver.client.constant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum EzyCommand implements EzyConstant {

    ERROR(10),
    HANDSHAKE(11),
    PING(12),
    PONG(13),
    DISCONNECT(14),
    LOGIN(20),
    LOGIN_ERROR(21),
    APP_ACCESS(30),
    APP_REQUEST(31),
    APP_EXIT(33),
    APP_ACCESS_ERROR(34),
    APP_REQUEST_ERROR(35),
    PLUGIN_INFO(40),
    PLUGIN_REQUEST_BY_NAME(41),
    PLUGIN_REQUEST_BY_ID(42);

    private final int id;

    private static final Map<Integer, EzyCommand> COMMANDS_BY_ID = commandsById();

    EzyCommand (int id) {
        this.id = id;
    }

    @Override
    public int getId () {
        return id;
    }

    @Override
    public String getName () {
        return toString();
    }

    public static EzyCommand valueOf (int id) {
        return COMMANDS_BY_ID.get(id);
    }

    private static Map<Integer, EzyCommand> commandsById () {
        Map<Integer, EzyCommand> map = new ConcurrentHashMap<>();
        for (EzyCommand cmd : values()) {
            map.put(cmd.getId(), cmd);
        }
        return map;
    }
}
