package world.estaria.proxy.player.connection.disconnect

import world.estaria.proxy.manager.api.ProxyConfig
import world.estaria.server.manager.api.ServerManagerApi

/**
 * @author Niklas Nieberler
 */

class NetworkIsFullDisconnectReasonHandler : PlayerDisconnectReasonHandler {

    override fun execute(proxyConfig: ProxyConfig, username: String, protocolVersion: Int): Boolean {
        val serverManager = ServerManagerApi.instance.serverManager
        val networkOnlineCount = serverManager.getProxyServers().sumOf { it.getOnlineCount() }
        if (proxyConfig.maximumPlayers > networkOnlineCount)
            return false
        return !proxyConfig.whitelistedPlayers.contains(username)
    }

}