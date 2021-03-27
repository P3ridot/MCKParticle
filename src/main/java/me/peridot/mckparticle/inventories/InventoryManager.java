package me.peridot.mckparticle.inventories;

import api.peridot.periapi.inventories.CustomInventory;
import api.peridot.periapi.inventories.PeriInventoryManager;
import me.peridot.mckparticle.MCKParticle;
import me.peridot.mckparticle.data.PluginConfiguration;
import me.peridot.mckparticle.user.UserCache;
import org.bukkit.event.inventory.InventoryType;

public class InventoryManager {

    private final MCKParticle plugin;
    private final PeriInventoryManager manager;

    private final CustomInventory effectsSelectInventory;
    private final CustomInventory colorSelectInventory;
    private final CustomInventory customColorSelectInventory;

    public InventoryManager(MCKParticle plugin) {
        this.plugin = plugin;
        this.manager = plugin.getPeriAPI().getInventoryManager();

        PluginConfiguration config = plugin.getConfiguration();

        effectsSelectInventory = CustomInventory.builder()
                .plugin(plugin)
                .manager(manager)
                .provider(new EffectsSelectInventory(plugin))
                .inventoryType(InventoryType.HOPPER)
                .title(config.getColoredString("inventories.effects_select.title"))
                .updateDelay(-1)
                .build();

        colorSelectInventory = CustomInventory.builder()
                .plugin(plugin)
                .manager(manager)
                .provider(new ColorSelectInventory(plugin))
                .inventoryType(InventoryType.HOPPER)
                .title(config.getColoredString("inventories.color_select.title"))
                .updateDelay(-1)
                .build();

        customColorSelectInventory = CustomInventory.builder()
                .plugin(plugin)
                .manager(manager)
                .provider(new CustomColorSelectInventory(plugin))
                .rows(3)
                .title(config.getColoredString("inventories.custom_color.title"))
                .updateDelay(-1)
                .build();
    }

    public CustomInventory getEffectsSelectInventory() {
        return effectsSelectInventory;
    }

    public CustomInventory getColorSelectInventory() {
        return colorSelectInventory;
    }

    public CustomInventory getCustomColorSelectInventory() {
        return customColorSelectInventory;
    }
}
