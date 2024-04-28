package world.estaria.proxy.player.connection.listener

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.player.KickedFromServerEvent
import world.estaria.proxy.player.connection.manager.FallbackServerManager

/**
 * @author Niklas Nieberler
 */

class KickedFromServerListener(
    private val fallbackServerManager: FallbackServerManager
) {

    @Subscribe
    fun handleKickedFromServer(event: KickedFromServerEvent) {
        this.fallbackServerManager.getFallbackLobbyServer().ifPresent {
            event.result = KickedFromServerEvent.RedirectPlayer.create(it)
        }
    }

}