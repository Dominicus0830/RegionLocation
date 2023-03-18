package com.dominicus0830.DORL.events;

import com.dominicus0830.DORL.RegionLocation;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class DORLEvent implements Listener {
    private final RegionLocation plugin;
    private Location center;
    private int radius;

    public DORLEvent(RegionLocation plugin) {
        this.plugin = plugin;
        this.center = plugin.getCenter();
        this.radius = plugin.getRadius();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();

        // DORL 구역 내부가 아니면 이벤트를 취소한다.
        if (!isInsideDORL(location)) {
            player.sendMessage(ChatColor.RED + "구역 밖이에요!");
            event.setCancelled(true);
        }
    }

    // 지정된 위치가 DORL 구역 내부인지 확인하는 메소드
    private boolean isInsideDORL(Location location) {
        // 현재 위치의 x, y, z 좌표
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        // DORL 구역의 중심 좌표
        Location center = plugin.getCenter();
        // DORL 구역의 반지름
        int radius = plugin.getRadius();

        // DORL 구역 내에 있는지 확인
        if (x >= center.getBlockX() - radius && x <= center.getBlockX() + radius
                && y >= center.getBlockY() - radius && y <= center.getBlockY() + radius
                && z >= center.getBlockZ() - radius && z <= center.getBlockZ() + radius) {
            return true;
        }
        return false;
    }
}