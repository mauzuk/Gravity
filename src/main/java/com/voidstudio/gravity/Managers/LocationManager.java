package com.voidstudio.gravity.Managers;

import com.voidstudio.gravity.Gravity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LocationManager implements Listener {
    private static final LocationManager instance = new LocationManager();
    public final Map<UUID, Location> lastLocation = new HashMap<>();
    private final CommandManager commandManager = CommandManager.getInstance();
    Gravity plugin = Gravity.getInstance();

    public static LocationManager getInstance() {
        return instance;
    }

    public void locationChecker() {
        new BukkitRunnable() {
            public void run() {
                if (commandManager.toggled) {
                    if (Bukkit.getOnlinePlayers().isEmpty()) return;
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        locationMapSetter(p);
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    private void locationMapSetter(Player p) {
        Location location = p.getLocation();
        UUID uuid = p.getUniqueId();

        if (!lastLocation.containsKey(uuid)) {
            lastLocation.put(uuid, location);
            p.sendMessage(ChatColor.GREEN + "You have been added to the Gravity list.");
            return;
        }

        if (location.equals(lastLocation.get(uuid))) {
            Bukkit.broadcastMessage(ChatColor.GREEN + p.getName() + ChatColor.RED + " was fired!");
            p.playSound(location, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
            p.setVelocity(p.getVelocity().setY(100));
        } else {
            lastLocation.put(uuid, location);
        }
    }
}
