package me.peridot.mckparticle.data;

import api.peridot.periapi.configuration.ConfigurationProvider;
import api.peridot.periapi.configuration.langapi.LangAPI;
import me.peridot.mckparticle.MCKParticle;
import org.bukkit.Color;

public class PluginConfiguration extends ConfigurationProvider {

    private final MCKParticle plugin;

    private LangAPI langAPI;

    public Color defaultColor;

    public PluginConfiguration(MCKParticle plugin) {
        super(plugin.getConfig().getConfigurationSection("config"));
        this.plugin = plugin;
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        langAPI = new LangAPI(plugin.getConfig().getConfigurationSection("config.messages"));

        defaultColor = Color.fromRGB(getInt("default_color.red"), getInt("default_color.green"), getInt("default_color.blue"));
    }

    public LangAPI getLangAPI() {
        return langAPI;
    }
}
