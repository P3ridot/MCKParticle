package me.peridot.mckparticle.user;

import org.bukkit.Color;
import org.bukkit.entity.Player;

import java.util.*;

public class User {

    private final Player player;
    private final UUID uuid;
    private List<EffectType> effect = new ArrayList<>();
    private Map<EffectType, Color> color = new HashMap<>();

    private EffectType effectToChangeColor;

    public User(final Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
    }

    public Player getPlayer() {
        return this.player;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setEffect(EffectType type, boolean status) {
        if (status) {
            if (!this.effect.contains(type)) {
                this.effect.add(type);
            }
        } else {
            if (this.effect.contains(type)) {
                this.effect.remove(type);
            }
        }
    }

    public void toogleEffect(EffectType type) {
        setEffect(type, !isEffectEnabled(type));
    }

    public void clearEffects() {
        this.effect.clear();
    }

    public boolean isEffectEnabled(EffectType type) {
        return this.effect.contains(type);
    }

    public List<EffectType> getEffects() {
        return this.effect;
    }

    public void setColor(EffectType type, Color color) {
        this.color.put(type, color);
    }

    public void addRed(EffectType type, int red) {
        Color color = getColor(type);
        setColor(type, color.setRed(Math.min(255, color.getRed() + red)));
    }

    public void addGreen(EffectType type, int green) {
        Color color = getColor(type);
        setColor(type, color.setGreen(Math.min(255, color.getGreen() + green)));
    }

    public void addBlue(EffectType type, int blue) {
        Color color = getColor(type);
        setColor(type, color.setBlue(Math.min(255, color.getBlue() + blue)));
    }

    public void removeRed(EffectType type, int red) {
        Color color = getColor(type);
        setColor(type, color.setRed(Math.max(0, color.getRed() - red)));
    }

    public void removeGreen(EffectType type, int green) {
        Color color = getColor(type);
        setColor(type, color.setGreen(Math.max(0, color.getGreen() - green)));
    }

    public void removeBlue(EffectType type, int blue) {
        Color color = getColor(type);
        setColor(type, color.setBlue(Math.max(0, color.getBlue() - blue)));
    }

    public Color getColor(EffectType type) {
        if (this.color.get(type) == null) {
            return Color.WHITE;
        }
        return this.color.get(type);
    }

    public EffectType getEffectToChangeColor() {
        return effectToChangeColor;
    }

    public void setEffectToChangeColor(EffectType effectToChangeColor) {
        this.effectToChangeColor = effectToChangeColor;
    }
}
