package me.peridot.mckparticle.user;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class UserCache {
    private static final Map<UUID, User> userMap = new HashMap<>();
    private static final Cache<UUID, User> userCache = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .build();

    public static void removeUser(UUID uuid) {
        final User user = userMap.get(uuid);
        if (user != null) {
            userCache.put(uuid, user);
        }
        userMap.remove(uuid);
    }

    public static User createUser(UUID uuid) {
        User user = userMap.get(uuid);
        if (user == null) {
            final User cachedUser = userCache.getIfPresent(uuid);
            if (cachedUser != null) {
                userMap.put(uuid, user = cachedUser);
                return user;
            }
            userMap.put(uuid, user = new User(Bukkit.getPlayer(uuid)));
        }
        return user;
    }
}
