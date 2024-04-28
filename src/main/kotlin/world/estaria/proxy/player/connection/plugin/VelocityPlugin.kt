package world.estaria.proxy.player.connection.plugin

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.event.proxy.ProxyReloadEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import world.estaria.proxy.player.connection.config.ConfigMapHandler
import world.estaria.proxy.player.connection.listener.KickedFromServerListener
import world.estaria.proxy.player.connection.listener.PlayerChooseInitialServerListener
import world.estaria.proxy.player.connection.listener.PreLoginListener
import world.estaria.proxy.player.connection.manager.FallbackServerManager

/**
 * @author Niklas Nieberler
 */

@Plugin(id = "proxy-player-connection", name = "proxy-player-connection", version = "1.0.1", authors = ["MrManHD"])
class VelocityPlugin @Inject constructor(
    private val server: ProxyServer
) {

    private val fallbackServerManager = FallbackServerManager(this.server)
    private val configMapHandler = ConfigMapHandler()

    @Subscribe
    fun handleInitialize(event: ProxyInitializeEvent) {
        val eventManager = this.server.eventManager
        eventManager.register(this, PreLoginListener(this.configMapHandler))
        eventManager.register(this, PlayerChooseInitialServerListener(this.fallbackServerManager))
        eventManager.register(this, KickedFromServerListener(this.fallbackServerManager))
    }

    @Subscribe
    fun handleProxyReload(event: ProxyReloadEvent) {
        this.configMapHandler.updateConfig()
    }

}