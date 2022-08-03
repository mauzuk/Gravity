package com.voidstudio.gravity;

import com.voidstudio.gravity.Managers.CommandManager;
import com.voidstudio.gravity.Managers.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Gravity extends JavaPlugin {
    private static Gravity instance;
    LocationManager locationManager = LocationManager.getInstance();

    @Override
    public void onEnable() {
        instance = this;
        registerEvents();
        registerCommands();
        locationManager.locationChecker();
    }

    @Override
    public void onDisable() {
        locationManager.lastLocation.clear();
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new LocationManager(), this);
    }

    private void registerCommands() {
        getCommand("gravity").setExecutor(new CommandManager());
    }

    public static Gravity getInstance() {
        return instance;
    }
}
