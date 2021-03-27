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

public class EffectsSelectInventory implements InventoryProvider {

    private final MCKParticle plugin;

    public EffectsSelectInventory(MCKParticle plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init(Player player, InventoryContent content) {
        PluginConfiguration config = plugin.getConfiguration();
        User user = UserCache.createUser(player.getUniqueId());

        content.clear();
        if (player.hasPermission("mckparticle.effect.ringo")) {
            String status = user.isEffectEnabled(EffectType.RINGO) ? config.getColoredString("messages.effect_status.enabled") : config.getColoredString("messages.effect_status.disabled");
            content.addItem(InventoryItem.builder()
                    .item(config.getItemBuilder("inventories.effects_select.buttons.ringo").clone()
                            .replaceInName(new Replacement("{STATUS}", status))
                            .replaceInLore(new Replacement("{STATUS}", status))
                    )
                    .consumer(event -> {
                        if (event.isShiftClick()) {
                            user.setEffectToChangeColor(EffectType.RINGO);
                            plugin.getInventoryManager().getColorSelectInventory().open(player);
                        } else {
                            user.toogleEffect(EffectType.RINGO);
                            user.setColor(EffectType.RINGO, config.defaultColor);
                        }
                    })
                    .update(true)
                    .build());
        }
        if (player.hasPermission("mckparticle.effect.spiral")) {
            String status = user.isEffectEnabled(EffectType.SPIRAL) ? config.getColoredString("messages.effect_status.enabled") : config.getColoredString("messages.effect_status.disabled");
            content.addItem(InventoryItem.builder()
                    .item(config.getItemBuilder("inventories.effects_select.buttons.spiral").clone()
                            .replaceInName(new Replacement("{STATUS}", status))
                            .replaceInLore(new Replacement("{STATUS}", status))
                    )
                    .consumer(event -> {
                        if (event.isShiftClick()) {
                            user.setEffectToChangeColor(EffectType.SPIRAL);
                            plugin.getInventoryManager().getColorSelectInventory().open(player);
                        } else {
                            user.toogleEffect(EffectType.SPIRAL);
                            user.setColor(EffectType.SPIRAL, config.defaultColor);
                        }
                    })
                    .update(true)
                    .build());
        }
        if (player.hasPermission("mckparticle.effect.wings")) {
            String status = user.isEffectEnabled(EffectType.WINGS) ? config.getColoredString("messages.effect_status.enabled") : config.getColoredString("messages.effect_status.disabled");
            content.addItem(InventoryItem.builder()
                    .item(config.getItemBuilder("inventories.effects_select.buttons.wings").clone()
                            .replaceInName(new Replacement("{STATUS}", status))
                            .replaceInLore(new Replacement("{STATUS}", status))
                    )
                    .consumer(event -> {
                        if (event.isShiftClick()) {
                            user.setEffectToChangeColor(EffectType.WINGS);
                            plugin.getInventoryManager().getColorSelectInventory().open(player);
                        } else {
                            user.toogleEffect(EffectType.WINGS);
                            user.setColor(EffectType.WINGS, config.defaultColor);
                        }
                    })
                    .update(true)
                    .build());
        }
        content.addItem(InventoryItem.builder()
                .item(config.getItemBuilder("inventories.effects_select.buttons.disable_all").clone())
                .consumer(event -> {
                    user.clearEffects();
                })
                .update(true)
                .build());
    }

    @Override
    public void update(Player player, InventoryContent content) {
    }

}
