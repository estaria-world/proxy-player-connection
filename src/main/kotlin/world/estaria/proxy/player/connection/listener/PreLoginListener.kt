package world.estaria.proxy.player.connection.listener

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.connection.PreLoginEvent
import world.avionik.minecraft.common.extension.text
import world.estaria.proxy.player.connection.config.ConfigMapHandler
import world.estaria.proxy.player.connection.disconnect.DisconnectReason

/**
 * @author Niklas Nieberler
 */

class PreLoginListener(
    private val configMapHandler: ConfigMapHandler
) {

    @Subscribe
    fun handlePreLogin(event: PreLoginEvent) {
        val config = this.configMapHandler.getConfig()
        val reasonType = DisconnectReason.Type.getReason(event.username, event.connection) ?: return
        val disconnectReasonMessage = config.getDisconnectReasonMessage(reasonType)
        event.result = PreLoginEvent.PreLoginComponentResult.denied(text(disconnectReasonMessage))
    }

}