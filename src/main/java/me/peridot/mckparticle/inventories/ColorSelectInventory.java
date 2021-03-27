package me.peridot.mckparticle.inventories;

import api.peridot.periapi.inventories.InventoryContent;
import api.peridot.periapi.inventories.items.InventoryItem;
import api.peridot.periapi.inventories.providers.InventoryProvider;
import api.peridot.periapi.utils.replacements.Replacement;
import me.peridot.mckparticle.MCKParticle;
import me.peridot.mckparticle.data.PluginConfiguration;
import me.peridot.mckparticle.user.EffectType;
import me.peridot.mckparticle.user.User;
import me.peridot.mckparticle.user.UserCache;
import org.bukkit.entity.Player;

public class ColorSelectInventory implements InventoryProvider {

    private final MCKParticle plugin;

    public ColorSelectInventory(MCKParticle plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init(Player player, InventoryContent content) {
        PluginConfiguration config = plugin.getConfiguration();
        User user = UserCache.createUser(player.getUniqueId());
        EffectType type = user.getEffectToChangeColor();

        if(type == null) return;

        content.clear();
        if (player.hasPermission("mckparticle.color.1")) {
            content.addItem(InventoryItem.builder()
                    .item(config.getItemBuilder("inventories.color_select.buttons.color1").clone())
                    .consumer(event -> {
                        user.setColor(type, config.getColor("inventories.color_select.buttons.color1.effect_color"));
                    })
                    .build());
        }
        if (player.hasPermission("mckparticle.color.2")) {
            content.addItem(InventoryItem.builder()
                    .item(config.getItemBuilder("inventories.color_select.buttons.color2").clone())
                    .consumer(event -> {
                        user.setColor(type, config.getColor("inventories.color_select.buttons.color2.effect_color"));
                    })
                    .build());
        }
        if (player.hasPermission("mckparticle.color.3")) {
            content.addItem(InventoryItem.builder()
                    .item(config.getItemBuilder("inventories.color_select.buttons.color3").clone())
                    .consumer(event -> {
                        user.setColor(type, config.getColor("inventories.color_select.buttons.color3.effect_color"));
                    })
                    .build());
        }
        if (player.hasPermission("mckparticle.color.4")) {
            content.addItem(InventoryItem.builder()
                    .item(config.getItemBuilder("inventories.color_select.buttons.color4").clone())
                    .consumer(event -> {
                        user.setColor(type, config.getColor("inventories.color_select.buttons.color4.effect_color"));
                    })
                    .build());
        }
        if (player.hasPermission("mckparticle.color.custom")) {
            content.addItem(InventoryItem.builder()
                    .item(config.getItemBuilder("inventories.color_select.buttons.custom").clone())
                    .consumer(event -> {
                        plugin.getInventoryManager().getCustomColorSelectInventory().open(player);
                    })
                    .build());
        }
    }

    @Override
    public void update(Player player, InventoryContent content) {
    }

}
