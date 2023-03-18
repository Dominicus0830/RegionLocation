package com.dominicus0830.DORL;

import com.dominicus0830.DORL.commands.DORLCommand;
import com.dominicus0830.DORL.events.DORLEvent;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class RegionLocation extends JavaPlugin {

    private static RegionLocation  plugin;

    private int radius;
    private Location center;

    public static RegionLocation getInstance(){return plugin;}

    @Override
    public void onEnable() {
        getLogger().info("플러그인이 활성화되었습니다.");
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        loadConfig();
        getCommand("dorl").setExecutor(new DORLCommand(this));
        getServer().getPluginManager().registerEvents(new DORLEvent(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("플러그인이 비활성화되었습니다.");
        saveConfig();
    }

    private void loadConfig() {
        radius = getConfig().getInt("radius", 0);
        int x = getConfig().getInt("center.x", 0);
        int y = getConfig().getInt("center.y", 0);
        int z = getConfig().getInt("center.z", 0);
        center = new Location(getServer().getWorlds().get(0), x, y, z);
    }

    public int getRadius() {
        return radius;
    }


    public Location getCenter() {
        return center;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setCenter(Location center) {
        this.center = center;
    }

}