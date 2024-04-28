package world.estaria.proxy.player.connection.manager

import com.velocitypowered.api.proxy.ProxyServer
import com.velocitypowered.api.proxy.server.RegisteredServer
import world.estaria.server.manager.api.ServerManagerApi
import java.util.*

/**
 * @author Niklas Nieberler
 */

class FallbackServerManager(
    private val proxyServer: ProxyServer
) {

    fun getFallbackLobbyServer(): Optional<RegisteredServer> {
        val lobbyServer = ServerManagerApi.instance.serverManager.getLobbyServers()
            .sortedBy { it.getOnlineCount() }
            .random()
        return this.proxyServer.getServer(lobbyServer.getName())
    }

}