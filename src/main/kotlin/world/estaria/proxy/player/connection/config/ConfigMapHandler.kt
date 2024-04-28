package world.estaria.proxy.player.connection.config

import io.fabric8.kubernetes.client.KubernetesClientBuilder
import world.estaria.kube.configmap.kit.KubeConfigMapKit

/**
 * @author Niklas Nieberler
 */

class ConfigMapHandler {

    private val kubernetesClient = KubernetesClientBuilder().build()

    private val configSerializer = Config.serializer()
    private val configName = "proxy-player-connection.yaml"
    private val configMapManager = KubeConfigMapKit.initializeKubeConfig("strela-system", kubernetesClient)

    init {
        if (!this.configMapManager.existsConfig(configName)) {
            this.configMapManager.createConfigMap(configName, configSerializer, Config.Default.get())
        }
    }

    fun updateConfig() {
        this.configMapManager.updateConfig(this.configName)
    }

    fun getConfig(): Config {
        return this.configMapManager.getConfig(this.configName, this.configSerializer)
            ?: throw NullPointerException("failed to find $configName")
    }

}