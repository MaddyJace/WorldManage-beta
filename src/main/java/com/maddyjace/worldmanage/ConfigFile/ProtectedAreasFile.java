package com.maddyjace.worldmanage.ConfigFile;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public enum ProtectedAreasFile {
    INSTANCE;

    private JavaPlugin plugin;
    private FileConfiguration targetFile;
    private Location location;


    // 需要在 onEnable() 初始化该 initialize 方法
    public void initialize(JavaPlugin plugin) {
        this.plugin = plugin;
        File filePath = new File(plugin.getDataFolder(), "protectedAreas.yml"); // 文件绝对路径
        targetFile = YamlConfiguration.loadConfiguration(filePath);          // 加载配置文件

    }

}
