package com.tvd12.ezyfoxserver.client.example.socket

import com.tvd12.ezyfoxserver.client.EzyClient
import com.tvd12.ezyfoxserver.client.EzyClients
import com.tvd12.ezyfoxserver.client.config.EzyClientConfig
import com.tvd12.ezyfoxserver.client.constant.EzyCommand
import com.tvd12.ezyfoxserver.client.entity.EzyApp
import com.tvd12.ezyfoxserver.client.entity.EzyArray
import com.tvd12.ezyfoxserver.client.entity.EzyData
import com.tvd12.ezyfoxserver.client.entity.EzyObject
import com.tvd12.ezyfoxserver.client.event.EzyConnectionFailureEvent
import com.tvd12.ezyfoxserver.client.event.EzyDisconnectionEvent
import com.tvd12.ezyfoxserver.client.event.EzyEventType
import com.tvd12.ezyfoxserver.client.factory.EzyEntityFactory
import com.tvd12.ezyfoxserver.client.handler.*
import com.tvd12.ezyfoxserver.client.request.EzyAppAccessRequest
import com.tvd12.ezyfoxserver.client.request.EzyLoginRequest
import com.tvd12.ezyfoxserver.client.request.EzyRequest
import com.tvd12.ezyfoxserver.client.socket.EzyMainEventsLoop

const val ZONE_NAME = "example"
const val APP_NAME = "hello-world"

class SocketClientProxy private constructor() {

    lateinit var username: String
    lateinit var password: String
    private var settedup: Boolean = false
    private lateinit var authenticatedCallback: () -> Unit
    private lateinit var messageCallback: (EzyObject) -> Unit
    private lateinit var disconnectedCallback: () -> Unit
    private lateinit var connectionFailedCallback: () -> Unit
    private lateinit var client: EzyClient

    companion object {
        private val INSTANCE = SocketClientProxy()

        fun getInstance() = INSTANCE
    }

    private fun setup() {
        val config = EzyClientConfig.builder()
            .clientName(ZONE_NAME)
            .build()
        val clients = EzyClients.getInstance()
        this.client = clients.newDefaultClient(config)
        this.client.setup()
            .addEventHandler(EzyEventType.DISCONNECTION, DisconnectionHandler {
                disconnectedCallback.invoke()
            })
            .addEventHandler(EzyEventType.CONNECTION_FAILURE, ConnectionFailureHandler {
                connectionFailedCallback.invoke()
            })
            .addDataHandler(EzyCommand.HANDSHAKE, HandshakeHandler())
            .addDataHandler(EzyCommand.LOGIN, LoginSuccessHandler())
            .addDataHandler(EzyCommand.APP_ACCESS, AppAccessHandler {
                authenticatedCallback.invoke()
            })
            .setupApp(APP_NAME)
            .addDataHandler("greet", GreetHandler {
                messageCallback.invoke(it)
            })
            .addDataHandler("secureChat", SecureMessageHandler {
                messageCallback.invoke(it)
            })
        val mainLoop = EzyMainEventsLoop()
        mainLoop.start()
    }

    fun connectToServer(username: String, password: String) {
        this.username = username
        this.password = password
        if (!settedup) {
            settedup = true
            setup()
        }
        this.client.connect("tvd12.com", 3005)
    }

    fun sendGreetRequest() {
        this.client.app.send(
            "greet",
            EzyEntityFactory.newObjectBuilder()
                .append("who", "Android Developer")
                .build()
        );
    }

    fun onAuthenticated(callback: () -> Unit) {
        this.authenticatedCallback = callback
    }

    fun onMessage(callback: (EzyObject) -> Unit) {
        this.messageCallback = callback
    }

    fun onDisconnectedCallback(callback: () -> Unit) {
        this.disconnectedCallback = callback
    }

    fun onConnectionFailedCallback(callback: () -> Unit) {
        this.connectionFailedCallback = callback
    }
}

internal class DisconnectionHandler(
    private val callback: () -> Unit
) : EzyDisconnectionHandler() {
    override fun postHandle(event: EzyDisconnectionEvent?) {
        callback.invoke()
    }
}

internal class ConnectionFailureHandler(
    private val callback: () -> Unit
) : EzyConnectionFailureHandler() {
    override fun onConnectionFailed(event: EzyConnectionFailureEvent?) {
        callback.invoke()
    }
}

internal class HandshakeHandler : EzyHandshakeHandler() {
    override fun getLoginRequest(): EzyRequest {
        return EzyLoginRequest(
            ZONE_NAME,
            SocketClientProxy.getInstance().username,
            SocketClientProxy.getInstance().password
        )
    }
}

internal class LoginSuccessHandler : EzyLoginSuccessHandler() {
    override fun handleLoginSuccess(responseData: EzyData?) {
        client.send(EzyAppAccessRequest(APP_NAME))
    }
}

internal class AppAccessHandler(
    private val callback: () -> Unit
) : EzyAppAccessHandler() {
    override fun postHandle(app: EzyApp?, data: EzyArray?) {
        callback.invoke()
    }
}

internal class GreetHandler(
    private val callback: (EzyObject) -> Unit
) : EzyAppDataHandler<EzyObject> {
    override fun handle(app: EzyApp, data: EzyObject) {
        callback.invoke(
            EzyEntityFactory.newObjectBuilder()
                .append("title", "Message")
                .append("content", data.get<String>("message"))
                .build()
        )
        app.send(
            "secureChat",
            EzyEntityFactory.newObjectBuilder()
                .append("who", "Young Monkey")
                .build()
        );
    }
}

internal class SecureMessageHandler(
    private val callback: (EzyObject) -> Unit
) : EzyAppDataHandler<EzyObject> {
    override fun handle(app: EzyApp?, data: EzyObject) {
        callback.invoke(
            EzyEntityFactory.newObjectBuilder()
                .append("title", "SSL Message")
                .append("content", data.get<String>("secure-message"))
                .build()
        )
    }
}
