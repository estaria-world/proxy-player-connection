package world.estaria.proxy.player.connection.config

import world.estaria.github.file.manager.properties.GitHubYamlLoader

/**
 * @author Niklas Nieberler
 */

class ConfigHandler {

    private val yamlLoader = GitHubYamlLoader("estaria-world/proxy-configurations/master/player-connection.yaml", Config.serializer())
    private var config = yamlLoader.getYaml()

    init {
        if (config == null)
            throw NullPointerException("failed to find player-connection.yaml in github")
    }

    fun updateConfig() {
        this.config = this.yamlLoader.getYaml()
    }

    fun getConfig(): Config {
        return this.config ?: throw NullPointerException("failed to find player-connection.yaml")
    }

}