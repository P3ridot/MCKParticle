package me.peridot.mckparticle.commands;

import api.peridot.periapi.utils.replacements.Replacement;
import me.peridot.mckparticle.MCKParticle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

public class ParticleCommand implements CommandExecutor {

    private final MCKParticle plugin;

    public ParticleCommand(MCKParticle plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("mckparticle.cmd")) {
            plugin.getConfiguration().getLangAPI().sendMessage(sender, "noperm", new Replacement("{PERMISSION}", "mckparticle.cmd"));
            return true;
        }
        plugin.getInventoryManager().getEffectsSelectInventory().open(player);
        return true;
    }

    public void registerCommand() {
        PluginCommand command = plugin.getCommand("particle");
        command.setExecutor(this);
    }
}
