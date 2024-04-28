package world.estaria.proxy.player.connection.disconnect

import world.estaria.proxy.manager.api.ProxyConfig

/**
 * @author Niklas Nieberler
 */

fun interface PlayerDisconnectReasonHandler {

    fun execute(proxyConfig: ProxyConfig, username: String, protocolVersion: Int): Boolean

}