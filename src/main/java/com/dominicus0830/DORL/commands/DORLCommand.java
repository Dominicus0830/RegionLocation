package com.dominicus0830.DORL.commands;

import com.dominicus0830.DORL.RegionLocation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class DORLCommand implements CommandExecutor, TabCompleter {

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
                RLn.getInstance().setRadius(radius);

            } else {
                sender.sendMessage("잘못된 명령어입니다.");
            }
            return true;
        }
        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> aaaaaa = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if (args.length == 1) {
            commands.add("반지름");

            StringUtil.copyPartialMatches(args[0], commands, aaaaaa);
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("반지름")) {
                commands.add("원하는 숫자 전부 가능");
                commands.add("1");
                commands.add("2");
                commands.add("3");
                commands.add("4");
                commands.add("5");
                commands.add("10");
                commands.add("50");
                commands.add("100");
                commands.add("250");
                commands.add("500");
                StringUtil.copyPartialMatches(args[1], commands, aaaaaa);
            }
        }

        return aaaaaa;
    }

}