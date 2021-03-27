package me.peridot.mckparticle.util;

import api.peridot.periapi.packets.PacketSender;
import api.peridot.periapi.packets.Reflection;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;

public class ColorParticleUtil {

    private Class<?> enumParticleClass;

    private Reflection.MethodInvoker getParticle;

    private Object enumParticle;

    private Reflection.ConstructorInvoker packetPlayOutWorldParticles;

    public ColorParticleUtil() {
        if (Reflection.serverVersionNumber <= 12) {
            enumParticleClass = Reflection.getMinecraftClass("EnumParticle");
            getParticle = Reflection.getMethod(enumParticleClass, "a", int.class);
            enumParticle = getParticle.invoke(enumParticleClass, 30);
            packetPlayOutWorldParticles = Reflection.getConstructor(Reflection.getMinecraftClass("PacketPlayOutWorldParticles"), enumParticleClass, boolean.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, int.class, int[].class);
        }
    }

    public void spawnParticle(Location location, int red, int green, int blue) {
        if (Reflection.serverVersionNumber <= 12) {
            Object packet = packetPlayOutWorldParticles.invoke(enumParticle, false, (float) location.getX(), (float) location.getY(), (float) location.getZ(), convertColor(red), convertColor(green), convertColor(blue), 1F, 0, null);
            Bukkit.getOnlinePlayers().stream()
                    .filter(player -> location.getWorld().equals(player.getWorld()))
                    .forEach(player -> {
                        PacketSender.sendPacket(player, packet);
                    });
        } else {
            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(red, green, blue), 1);
            location.getWorld().spawnParticle(Particle.REDSTONE, location, 1, dustOptions);
        }
    }

    private float convertColor(int color) {
        return (color * 0.007843F) - 1F;
    }
}