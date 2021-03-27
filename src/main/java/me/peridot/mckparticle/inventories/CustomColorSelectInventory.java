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
import org.bukkit.Color;
import org.bukkit.entity.Player;

public class CustomColorSelectInventory implements InventoryProvider {

    private final MCKParticle plugin;

    public CustomColorSelectInventory(MCKParticle plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init(Player player, InventoryContent content) {
        PluginConfiguration config = plugin.getConfiguration();
        User user = UserCache.createUser(player.getUniqueId());
        EffectType type = user.getEffectToChangeColor();

        if(type == null) return;

        Color color = user.getColor(type);

        content.clear();
        content.setItem(2, 5, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.current_color").clone()
                        .replaceInName(new Replacement("{RED}", color.getRed()),
                                new Replacement("{GREEN}", color.getGreen()),
                                new Replacement("{BLUE}", color.getBlue()))
                        .replaceInLore(new Replacement("{RED}", color.getRed()),
                                new Replacement("{GREEN}", color.getGreen()),
                                new Replacement("{BLUE}", color.getBlue()))
                        .setLeatherArmorColor(color)
                )
                .build());

        content.setItem(1, 1, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.red.decrease.one").clone())
                .consumer(event -> {
                    user.removeRed(type, 1);
                })
                .update(true)
                .build());

        content.setItem(1, 2, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.red.decrease.ten").clone())
                .consumer(event -> {
                    user.removeRed(type, 10);
                })
                .update(true)
                .build());

        content.setItem(1, 3, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.red.decrease.max").clone())
                .consumer(event -> {
                    user.removeRed(type, 255);
                })
                .update(true)
                .build());

        content.setItem(1, 7, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.red.increase.one").clone())
                .consumer(event -> {
                    user.addRed(type, 1);
                })
                .update(true)
                .build());

        content.setItem(1, 8, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.red.increase.ten").clone())
                .consumer(event -> {
                    user.addRed(type, 10);
                })
                .update(true)
                .build());

        content.setItem(1, 9, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.red.increase.max").clone())
                .consumer(event -> {
                    user.addRed(type, 255);
                })
                .update(true)
                .build());

        content.setItem(2, 1, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.green.decrease.one").clone())
                .consumer(event -> {
                    user.removeGreen(type, 1);
                })
                .update(true)
                .build());

        content.setItem(2, 2, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.green.decrease.ten").clone())
                .consumer(event -> {
                    user.removeGreen(type, 10);
                })
                .update(true)
                .build());

        content.setItem(2, 3, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.green.decrease.max").clone())
                .consumer(event -> {
                    user.removeGreen(type, 255);
                })
                .update(true)
                .build());

        content.setItem(2, 7, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.green.increase.one").clone())
                .consumer(event -> {
                    user.addGreen(type, 1);
                })
                .update(true)
                .build());

        content.setItem(2, 8, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.green.increase.ten").clone())
                .consumer(event -> {
                    user.addGreen(type, 10);
                })
                .update(true)
                .build());

        content.setItem(2, 9, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.green.increase.max").clone())
                .consumer(event -> {
                    user.addGreen(type, 255);
                })
                .update(true)
                .build());

        content.setItem(3, 1, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.blue.decrease.one").clone())
                .consumer(event -> {
                    user.removeBlue(type, 1);
                })
                .update(true)
                .build());

        content.setItem(3, 2, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.blue.decrease.ten").clone())
                .consumer(event -> {
                    user.removeBlue(type, 10);
                })
                .update(true)
                .build());

        content.setItem(3, 3, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.blue.decrease.max").clone())
                .consumer(event -> {
                    user.removeBlue(type, 255);
                })
                .update(true)
                .build());

        content.setItem(3, 7, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.blue.increase.one").clone())
                .consumer(event -> {
                    user.addBlue(type, 1);
                })
                .update(true)
                .build());

        content.setItem(3, 8, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.blue.increase.ten").clone())
                .consumer(event -> {
                    user.addBlue(type, 10);
                })
                .update(true)
                .build());

        content.setItem(3, 9, InventoryItem.builder()
                .item(config.getItemBuilder("inventories.custom_color.buttons.blue.increase.max").clone())
                .consumer(event -> {
                    user.addBlue(type, 255);
                })
                .update(true)
                .build());
    }

    @Override
    public void update(Player player, InventoryContent content) {
    }

}
