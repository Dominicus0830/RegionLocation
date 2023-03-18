package com.dominicus0830.DORL.events;

import com.dominicus0830.DORL.RegionLocation;
import com.dominicus0830.DORL.func.DORLfunc;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DORLEvent implements Listener {
    private RegionLocation plugin = RegionLocation.getInstance();
    private Location center;
    private int radius;

    public DORLEvent(RegionLocation plugin) {
        this.plugin = plugin;
        this.center = plugin.getCenter();
        this.radius = plugin.getRadius();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Location location = event.getEntity().getLocation();

        // DORL 구역 내부가 아니면 이벤트를 취소한다.
        if (!new DORLfunc().isInsideDORL(location)) {
            event.setDeathMessage(null);
        }
    }

    // 지정된 위치가 DORL 구역 내부인지 확인하는 메소드

}