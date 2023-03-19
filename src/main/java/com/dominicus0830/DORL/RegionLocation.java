package com.dominicus0830.DORL;

import com.dominicus0830.DORL.commands.DORLCommand;
import com.dominicus0830.DORL.events.DORLEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.plugin.java.JavaPlugin;

public class RegionLocation extends JavaPlugin {

    private int radius;
    private static RegionLocation instance;

    @Override
    public void onEnable() {
        getLogger().info("플러그인이 활성화되었습니다.");
        getLogger().info("이 플러그인은 LIP 커뮤니티에서 제작되었습니다");
        getLogger().info("제작자: dominicus0830");
        getLogger().info("플러그인 버전: 1.0.0");
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        loadConfig();
        getCommand("dorl").setExecutor(new DORLCommand(this));
        getCommand("dorl").setTabCompleter(new DORLCommand(this));
        getServer().getPluginManager().registerEvents(new DORLEvent(this), this);
        instance = this;
        Bukkit.getWorlds().forEach(w -> w.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false));
        Bukkit.getWorlds().forEach(w -> w.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false));
    }


    @Override
    public void onDisable() {
        getLogger().info("플러그인이 비활성화되었습니다.");
        saveConfig();
    }

    public static RegionLocation getInstance() {
        return instance;
    }

    private void loadConfig() {
        radius = getConfig().getInt("radius", 500);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }


}

