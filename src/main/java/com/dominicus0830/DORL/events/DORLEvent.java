package com.dominicus0830.DORL.events;

import com.dominicus0830.DORL.RegionLocation;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

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

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();

        // DORL 구역 내부가 아니면 이벤트를 취소한다.
        if (!isInsideDORL(location)) {
            player.sendMessage(ChatColor.RED + "구역 밖이에요!");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent event) {
        Player player = event.getPlayer();
        Advancement advancement = event.getAdvancement();
        String advancementName = advancement.getKey().getKey();

        if (isInsideDORL(player.getLocation())) {
            if (advancement instanceof AdvancementProgress) {
                AdvancementProgress advancementProgress = (AdvancementProgress) advancement;
                if (advancementProgress.isDone()) {
                    // Secret Advancements을 완료한 경우
                    player.sendMessage(ChatColor.GREEN + "Secret Advancement " + advancement.getKey().getKey() + " secert " + player.getName());
                }
            } else if (advancement instanceof Advancement) {
                // Challenge Advancements을 완료한 경우
                player.sendMessage(ChatColor.LIGHT_PURPLE + player.getName() + " Challenge " + advancement.getKey().getKey());
            }
        }

        //player.sendMessage(ChatColor.GOLD + "발전과제를 달성하였습니다!");
        player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (isInsideDORL(player.getLocation())) {
            // 구역 안에 있는 경우
            String deathMessage = event.getDeathMessage();
            event.setDeathMessage(deathMessage); // 사망 메세지 보이기
        }
    }

    @EventHandler
    public void onDPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (!isInsideDORL(player.getLocation())) {
            this.onPlayerDeath(event);
        }
    }

    // 지정된 위치가 DORL 구역 내부인지 확인하는 메소드
    private boolean isInsideDORL(Location location) {
        // 현재 위치의 x, z 좌표
        int x = location.getBlockX();
        int z = location.getBlockZ();

        // DORL 구역의 중심 좌표
        Location center = plugin.getCenter();
        // DORL 구역의 반지름
        int radius = plugin.getRadius();

        // DORL 구역 내에 있는지 확인
        if (x >= center.getBlockX() - radius && x <= center.getBlockX() + radius
                && z >= center.getBlockZ() - radius && z <= center.getBlockZ() + radius) {
            return true;
        }
        return false;
    }
}