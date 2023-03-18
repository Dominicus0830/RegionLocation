package com.dominicus0830.DORL.commands;

import com.dominicus0830.DORL.RegionLocation;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DORLCommand implements CommandExecutor {

    private final Plugin plugin;
    private final FileConfiguration config;

    private RegionLocation RLn;

    public DORLCommand(Plugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        config.options().copyDefaults(true);
        plugin.saveDefaultConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("dorl")) {
            if (args.length == 2 && args[0].equalsIgnoreCase("반지름")) {
                int radius = Integer.parseInt(args[1]);
                config.set("radius", radius);
                plugin.saveConfig();
                sender.sendMessage("반지름이 " + radius + "로 설정되었습니다.");
            } else if (args.length == 1 && args[0].equalsIgnoreCase("구역") && sender instanceof Player) {
                Player player = (Player) sender;
                Location location = player.getLocation();
                int x = location.getBlockX();
                int y = location.getBlockY();
                int z = location.getBlockZ();
                config.set("center.x", x);
                config.set("center.y", y);
                config.set("center.z", z);
                //RLn.setCenter(player.getLocation());

                plugin.saveConfig();
                sender.sendMessage("구역 좌표가 (" + x + ", " + y + ", " + z + ")로 설정되었습니다.");
            } else {
                sender.sendMessage("잘못된 명령어입니다.");
            }
            return true;
        }
        return false;
    }

}

