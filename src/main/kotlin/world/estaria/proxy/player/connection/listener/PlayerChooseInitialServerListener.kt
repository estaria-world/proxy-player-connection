package world.estaria.proxy.player.connection.listener

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.player.PlayerChooseInitialServerEvent
import world.estaria.proxy.player.connection.manager.FallbackServerManager

/**
 * @author Niklas Nieberler
 */

class PlayerChooseInitialServerListener(
    private val fallbackServerManager: FallbackServerManager
) {

    @Subscribe
    fun handlePlayerChooseInitialServer(event: PlayerChooseInitialServerEvent) {
        this.fallbackServerManager.getFallbackLobbyServer().ifPresent {
            event.setInitialServer(it)
        }
    }

}