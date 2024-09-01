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
        val lobbyServers = ServerManagerApi.instance.serverManager.getLobbyServers()
        if (lobbyServers.isEmpty())
            return Optional.ofNullable(null)
        val lobbyServer = lobbyServers
            .sortedBy { it.getOnlineCount() }
            .random()
        return this.proxyServer.getServer(lobbyServer.getName())
    }

}