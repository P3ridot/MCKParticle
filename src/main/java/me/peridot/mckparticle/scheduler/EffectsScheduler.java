package me.peridot.mckparticle.scheduler;

import me.peridot.mckparticle.MCKParticle;
import me.peridot.mckparticle.user.EffectType;
import me.peridot.mckparticle.user.User;
import me.peridot.mckparticle.user.UserCache;
import me.peridot.mckparticle.util.ColorParticleUtil;
import me.peridot.mckparticle.util.PointRotor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EffectsScheduler {

    private final MCKParticle plugin;

    public EffectsScheduler(MCKParticle plugin) {
        this.plugin = plugin;
    }

    public void start() {
        ColorParticleUtil color = new ColorParticleUtil();
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            long rotation = (((System.currentTimeMillis() / 50) % 1000) % 36) * 10;
            for (Player player : Bukkit.getOnlinePlayers()) {
                User user = UserCache.createUser(player.getUniqueId());
                Location pLoc = player.getLocation();
                World world = pLoc.getWorld();
                pLoc.add(0, 0.2, 0);
                double angleRight = pLoc.getYaw() - 60;
                double angleLeft = pLoc.getYaw() + 60;
                double[] cordRight = PointRotor.rotatePointDeg(new double[]{pLoc.getX(), pLoc.getZ() - 0.8}, new double[]{pLoc.getX(), pLoc.getZ()}, angleRight, 0.4);
                double[] cordLeft = PointRotor.rotatePointDeg(new double[]{pLoc.getX(), pLoc.getZ() - 0.8}, new double[]{pLoc.getX(), pLoc.getZ()}, angleLeft, 0.4);
                if (user.isEffectEnabled(EffectType.WINGS)) {
                    int red = user.getColor(EffectType.WINGS).getRed();
                    int green = user.getColor(EffectType.WINGS).getGreen();
                    int blue = user.getColor(EffectType.WINGS).getBlue();
                    Location endRight = new Location(world, cordRight[0], pLoc.getY() + 0.7, cordRight[1]);
                    Location endLeft = new Location(world, cordLeft[0], pLoc.getY() + 0.7, cordLeft[1]);
                    Location endCenter = new Location(world, ((endLeft.getX() + endRight.getX()) / 2), pLoc.getY() + 0.7, ((endLeft.getZ() + endRight.getZ()) / 2));
                    Location endCenterUp = new Location(world, endCenter.getX(), endCenter.getY() + 0.6, endCenter.getZ());
                    Location endCenterRight = new Location(world, ((endRight.getX() + endCenter.getX()) / 2), pLoc.getY() + 0.95, ((endRight.getZ() + endCenter.getZ()) / 2));
                    Location endCenterLeft = new Location(world, ((endLeft.getX() + endCenter.getX()) / 2), pLoc.getY() + 0.95, ((endLeft.getZ() + endCenter.getZ()) / 2));
                    drawParticleLine(endCenter, endCenterUp, 10).forEach(particle -> {
                        color.spawnParticle(particle, red, green, blue);
                    });
                    for (int height = 0; height < 2; height++) {
                        drawParticleLine(endRight, endCenterRight, 4).forEach(particle -> {
                            color.spawnParticle(particle, red, green, blue);
                        });
                        drawParticleLine(endLeft, endCenterLeft, 4).forEach(particle -> {
                            color.spawnParticle(particle, red, green, blue);
                        });
                        if (height == 1) {
                            drawParticleLine(endCenterLeft, endCenterUp, 4).forEach(particle -> {
                                color.spawnParticle(particle, red, green, blue);
                            });
                            drawParticleLine(endCenterRight, endCenterUp, 4).forEach(particle -> {
                                color.spawnParticle(particle, red, green, blue);
                            });
                        } else {
                            drawParticleLine(endCenterLeft, endCenter, 4).forEach(particle -> {
                                color.spawnParticle(particle, red, green, blue);
                            });
                            drawParticleLine(endCenterRight, endCenter, 4).forEach(particle -> {
                                color.spawnParticle(particle, red, green, blue);
                            });
                        }
                        endCenterRight.add(0, 0.7, 0);
                        endCenterLeft.add(0, 0.7, 0);
                    }
                }
                if (user.isEffectEnabled(EffectType.RINGO)) {
                    int red = user.getColor(EffectType.RINGO).getRed();
                    int green = user.getColor(EffectType.RINGO).getGreen();
                    int blue = user.getColor(EffectType.RINGO).getBlue();
                    for (int degree = 0; degree < 18; degree++) {
                        double radians = Math.toRadians(degree * 20);
                        double x = Math.cos(radians);
                        double z = Math.sin(radians);
                        pLoc.add(x, 0, z);
                        try {
                            color.spawnParticle(pLoc, red, green, blue);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        pLoc.subtract(x, 0, z);
                    }
                }
                if (user.isEffectEnabled(EffectType.SPIRAL)) {
                    int red = user.getColor(EffectType.SPIRAL).getRed();
                    int green = user.getColor(EffectType.SPIRAL).getGreen();
                    int blue = user.getColor(EffectType.SPIRAL).getBlue();
                    for (int degree = 0; degree < 12; degree++) {
                        double radians = Math.toRadians(rotation + (degree * 30));
                        double x = Math.cos(radians);
                        double z = Math.sin(radians);
                        pLoc.add(x, 0.15, z);
                        try {
                            color.spawnParticle(pLoc, red, green, blue);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        pLoc.subtract(x, 0, z);
                    }
                }
            }
        }, 0, 1);
    }

    private List<Location> drawParticleLine(Location from, Location to, double density) {
        List<Location> list = new ArrayList<>();
        int n = (int) Math.round(density * from.distance(to));
        double x = from.getX(), y = from.getY(), z = from.getZ();
        double dX = (to.getX() - x) / n;
        double dY = (to.getY() - y) / n;
        double dZ = (to.getZ() - z) / n;
        list.add(from);
        for (int i = 1; i < n; i++)
            list.add(new Location(from.getWorld(), x + i * dX, y + i * dY, z + i * dZ));
        return list;
    }
}
