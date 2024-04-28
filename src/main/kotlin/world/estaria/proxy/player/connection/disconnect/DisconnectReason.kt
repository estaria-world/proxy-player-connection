package world.estaria.proxy.player.connection.disconnect

import com.velocitypowered.api.proxy.InboundConnection
import kotlinx.serialization.Serializable
import world.estaria.proxy.manager.api.ProxyManagerApi

/**
 * @author Niklas Nieberler
 */

@Serializable
class DisconnectReason(
    val type: Type,
    val message: String
) {

    enum class Type(
        private val reasonHandler: PlayerDisconnectReasonHandler
    ) {

        NETWORK_IN_MAINTENANCE({ config, username, _ ->
            config.maintenance && !config.whitelistedPlayers.contains(username)
        }),

        NETWORK_IS_FULL(NetworkIsFullDisconnectReasonHandler()),

        UNSUPPORTED_VERSION({ config, _, protocolVersion ->
            !config.supportedProtocolVersions.contains(protocolVersion)
        });

        fun invoke(username: String, protocolVersion: Int): Boolean {
            val config = ProxyManagerApi.instance.configHandler.getConfig()
            return this.reasonHandler.execute(config, username, protocolVersion)
        }

        companion object {
            fun getReason(username: String, connection: InboundConnection): Type? {
                return entries.firstOrNull { it.invoke(username, connection.protocolVersion.protocol) }
            }
        }
    }

}