package com.maddyjace.worldmanage.Commands;

import com.maddyjace.worldmanage.ConfigFile.MessageFile;
import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Commands implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            args = new String[] { "help" };
        }

        String subCommand = args[0].toLowerCase();
        MessageFile getMessage = MessageFile.INSTANCE;
        switch (subCommand) {
            case "reload":
                WorldFile.INSTANCE.reload();
                MessageFile.INSTANCE.reload();

                sender.sendMessage(ChatColor.DARK_GRAY + "[AdvancedCommandBlocker]: " + ChatColor.GREEN + getMessage.getMessage("Reload"));
                return true;
            default:
                sender.sendMessage(ChatColor.DARK_GRAY + "[AdvancedCommandBlocker]: " + ChatColor.DARK_RED + getMessage.getMessage("CommandCorrectUsage"));
                return true;

        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {

        if (!sender.hasPermission("worldmanage.admin")) {
            return Collections.emptyList();
        }
        if (args.length == 1) {
            return Stream.of("help", "reload")
                    .filter(cmd -> cmd.startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList()); // Java 16+ 用 .toList()，Java 8 用 collect(Collectors.toList())
        }
        return Collections.emptyList();
    }

}
