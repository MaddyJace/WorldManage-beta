package com.maddyjace.worldmanage;

import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import com.maddyjace.worldmanage.Listener.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class WorldManage extends JavaPlugin {

    @Override
    public void onEnable() {

        // 载入 config.yml 和 world.yml 文件
        saveDefaultConfig();
        saveResource("world.yml", false);

        // 初始化 Listener
        // getServer().getPluginManager().registerEvents(new ProtectedAreas(),  this);
        // 注册 点燃方块 监听器
        getServer().getPluginManager().registerEvents(new BlockIgnite(),  this);
        // 注册 火焰传播 监听器
        getServer().getPluginManager().registerEvents(new FlameSpread(),  this);
        // 注册 树叶自然衰减 监听器
        getServer().getPluginManager().registerEvents(new LeavesDecay(),  this);
        // 注册 方块被破坏 监听器
        getServer().getPluginManager().registerEvents(new BlockBreak(),  this);


        WorldFile.INSTANCE.initialize(this);

//        if(WorldFile.INSTANCE.playerRules("world", "blockIgnite")) {
//            System.out.println("找到了数据");
//        }

        Bukkit.getConsoleSender().sendMessage("§b§l");
        Bukkit.getConsoleSender().sendMessage("§b§l");
        Bukkit.getConsoleSender().sendMessage("§b§l _       __           __    ____  ___                           ");
        Bukkit.getConsoleSender().sendMessage("§b§l| |     / /___  _____/ /___/ /  |/  /___ _____  ____ _____ ____ ");
        Bukkit.getConsoleSender().sendMessage("§b§l| | /| / / __ \\/ ___/ / __  / /|_/ / __ `/ __ \\/ __ `/ __ `/ _ \\");
        Bukkit.getConsoleSender().sendMessage("§b§l| |/ |/ / /_/ / /  / / /_/ / /  / / /_/ / / / / /_/ / /_/ /  __/");
        Bukkit.getConsoleSender().sendMessage("§b§l|__/|__/\\____/_/  /_/\\__,_/_/  /_/\\__,_/_/ /_/\\__,_/\\__, /\\___/ ");
        Bukkit.getConsoleSender().sendMessage("§b§l                                                   /____/       ");
        Bukkit.getConsoleSender().sendMessage("§b§l");
        Bukkit.getConsoleSender().sendMessage("§b§l");

    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
