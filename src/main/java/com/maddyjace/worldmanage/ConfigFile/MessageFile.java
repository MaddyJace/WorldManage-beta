package com.maddyjace.worldmanage.ConfigFile;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public enum MessageFile {
    INSTANCE;

    private Plugin plugin;
    private Plugin placeholderAPI;
    private YamlConfiguration messageFile;

    // 需要在 onEnable() 初始化该 initialize 方法
    public void initialize(Plugin plugin, Plugin placeholderAPI) {
        this.plugin = plugin;
        this.placeholderAPI = placeholderAPI;
        File filePath = new File(plugin.getDataFolder(), "message.yml"); // 文件绝对路径
        messageFile = YamlConfiguration.loadConfiguration(filePath);          // 加载配置文件
    }

    // 重载配置文件
    public void reload() {
        initialize(plugin, placeholderAPI);
    }

    public boolean isPlaceholderAPILoaded() {
        return placeholderAPI != null;
    }

    public static void parsePlaceholders(Player player, String input) {
        if(input != null) {

            // 检查是否安装 PlaceholderAPI
            if(MessageFile.INSTANCE.isPlaceholderAPILoaded()) {
                // 解析PaPi占位符
                String parsedPaPi = PlaceholderAPI.setPlaceholders(player, input);
                // 解析Minecraft颜色
                String parsedColor = parsedPaPi.replace("&", "§");
                // 向玩家发送信息
                player.sendMessage(parsedColor);
            } else {
                String parsedColor = input.replace("&", "§");
                player.sendMessage(parsedColor);
            }

        }

    }

    public static void sendMessageToTheConsole(String message) {
        if(message != null) {
            // 解析Minecraft颜色
            String parsedColor = message.replace("&", "§");
            // 向控制台发送信息
            Bukkit.getConsoleSender().sendMessage(parsedColor);
        }
    }

    public String getMessage(String message) {
        if(message != null) {
            return messageFile.getString(message);
        }
        return null;
    }

}
