package me.peridot.mckparticle.listeners;

import me.peridot.mckparticle.user.UserCache;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public static void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UserCache.removeUser(player.getUniqueId());
    }
}
