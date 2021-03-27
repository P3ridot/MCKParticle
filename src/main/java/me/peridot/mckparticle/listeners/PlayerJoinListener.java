package me.peridot.mckparticle.listeners;

import me.peridot.mckparticle.user.UserCache;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public static void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UserCache.createUser(player.getUniqueId());
    }
}
