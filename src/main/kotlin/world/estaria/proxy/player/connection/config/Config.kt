package world.estaria.proxy.player.connection.config

import kotlinx.serialization.Serializable
import world.estaria.proxy.player.connection.disconnect.DisconnectReason

/**
 * @author Niklas Nieberler
 */

@Serializable
class Config(
    private val disconnectReasons: List<DisconnectReason>
) {

    fun getDisconnectReasonMessage(type: DisconnectReason.Type): String {
        return this.disconnectReasons.first { it.type == type }.message
    }

    object Default {
        fun get(): Config {
            return Config(
                DisconnectReason.Type.entries.map { DisconnectReason(it, "") }
            )
        }
    }

}