package com.dominicus0830.DORL.events;

import com.dominicus0830.DORL.RegionLocation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class DORLEvent implements Listener {

    private RegionLocation RLn;
    private Location center;
    private int radius;

    public DORLEvent(RegionLocation plugin) {
        this.RLn = plugin;
        this.radius = plugin.getRadius();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Entity killer = event.getEntity().getKiller();
        Location deathLocation = player.getLocation().add(0, 1, 0);
        this.radius = RLn.getRadius();

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.equals(player)) {
                continue;
            }
            if (p.getLocation().distance(deathLocation) <= radius) {
                if (killer instanceof Player) {//English
                    p.sendMessage(ChatColor.RED + player.getName() + " was kill by " + killer.getName());
                } else {
                    p.sendMessage(ChatColor.RED + player.getName() + "was died.");
                }
            }
        }
    }

    @EventHandler
    public void onOpPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(player.isOp()){
            player.sendMessage(ChatColor.GREEN+"이 메세지는 op 만 보입니다.");
            player.sendMessage(ChatColor.GREEN+"플러그인이 활성화되었습니다.");
            player.sendMessage(ChatColor.GREEN+"이 플러그인은 LIP 커뮤니티에서 제작되었습니다");
            player.sendMessage(ChatColor.GREEN+"제작자: dominicus0830");
            player.sendMessage(ChatColor.GREEN+"플러그인 버전: 1.0.0");
        }
    }

    @EventHandler
    public void onAdvancementDone(PlayerAdvancementDoneEvent event) {
        Player player = event.getPlayer();
        Location ADLocation = player.getLocation().add(0, 1, 0);
        this.radius = RLn.getRadius();
        String advancement = event.getAdvancement().getDisplay().getTitle();

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.equals(player)) {
                continue;
            }
            if (p.getLocation().distance(ADLocation) <= radius) {
                p.sendMessage(ChatColor.GREEN + player.getName() + " has made the advancement [" + advancement + "]");
            }
        }
    }

}