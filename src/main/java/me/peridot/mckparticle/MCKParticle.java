package me.peridot.mckparticle;

import api.peridot.periapi.PeriAPI;
import me.peridot.mckparticle.bstats.bukkit.Metrics;
import me.peridot.mckparticle.commands.ParticleCommand;
import me.peridot.mckparticle.data.PluginConfiguration;
import me.peridot.mckparticle.inventories.InventoryManager;
import me.peridot.mckparticle.listeners.PlayerJoinListener;
import me.peridot.mckparticle.listeners.PlayerQuitListener;
import me.peridot.mckparticle.scheduler.EffectsScheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class MCKParticle extends JavaPlugin {

    private PluginConfiguration pluginConfiguration;
    private PeriAPI periAPI;
    private InventoryManager inventoryManager;

    @Override
    public void onEnable() {
        PluginDescriptionFile description = getDescription();

        Logger logger = Bukkit.getLogger();
        if (!description.getName().equalsIgnoreCase("MCKParticle") && !description.getAuthors().get(0).equalsIgnoreCase("Peridot (McKoxu)")) {
            logger.severe("[MCKParticle] Wykryto zmiane plugin.yml!");
            logger.severe("[MCKParticle] Wykryto: " + description.getName() + ", " + description.getAuthors().get(0));
            logger.severe("[MCKParticle] Poprawnie: MCKParticle, Peridot (McKoxu)");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        pluginConfiguration = new PluginConfiguration(this);
        pluginConfiguration.loadConfig();

        periAPI = new PeriAPI(this);
        periAPI.init();

        inventoryManager = new InventoryManager(this);

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);

        new ParticleCommand(this).registerCommand();

        new EffectsScheduler(this).start();

        int pluginId = 6275;
        new Metrics(this, pluginId);
    }

    public PluginConfiguration getConfiguration() {
        return pluginConfiguration;
    }

    public PeriAPI getPeriAPI() {
        return periAPI;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }
}
