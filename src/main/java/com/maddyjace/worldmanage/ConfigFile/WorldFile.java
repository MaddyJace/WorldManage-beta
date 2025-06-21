package com.maddyjace.worldmanage.ConfigFile;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public enum WorldFile {
    INSTANCE;

    private JavaPlugin plugin;

    private FileConfiguration worldFile;

    private Location location;
    private String rules;


    // 需要在 onEnable() 初始化该 initialize 方法
    public void initialize(JavaPlugin plugin) {
        this.plugin = plugin;
        File filePath = new File(plugin.getDataFolder(), "world.yml"); // 文件绝对路径
        worldFile = YamlConfiguration.loadConfiguration(filePath);          // 加载配置文件
    }

    public boolean globalRules(String currentWorld,String type) {
        for(String worldName : worldFile.getKeys(false)) {
            ConfigurationSection section = worldFile.getConfigurationSection(worldName);
            try {
                String world = section.getString("worldNane");
                ConfigurationSection global = section.getConfigurationSection("globalRules");
                if(world.equals(currentWorld) && global.getBoolean(type)) {
                    return true;
                }
            } catch (Exception e) {
                return  false;
            }
        }
        return false;           
    }


    public boolean playerRules(String currentWorld,String type,Player player) {
        for(String worldName : worldFile.getKeys(false)) {
            ConfigurationSection section = worldFile.getConfigurationSection(worldName);
            try {
                String world = section.getString("worldNane");
                String permission = section.getString("permission");
                ConfigurationSection global = section.getConfigurationSection("playerRules");
                if(!hasPermission(player, permission) && world.equals(currentWorld) && global.getBoolean(type)) {
                    return true;
                }
            } catch (Exception e) {
                return  false;
            }
        }
        return false;
    }

    /**
     * 该静态方法判定玩家是否有指定的权限
     */
    public static boolean hasPermission(Player player, String permission) {
        if (player == null || permission == null) return false;
        return player.hasPermission(permission);

    }

}
