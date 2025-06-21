package com.maddyjace.worldmanage;

import com.maddyjace.worldmanage.Commands.Commands;
import com.maddyjace.worldmanage.ConfigFile.FileWatcher;
import com.maddyjace.worldmanage.ConfigFile.MessageFile;
import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import com.maddyjace.worldmanage.ListenerPlayerRules.*;
import com.maddyjace.worldmanage.ListenerGlobalRules.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class WorldManage extends JavaPlugin {

    private final FileWatcher watcher = new FileWatcher(getDataFolder().getAbsolutePath(), ".yml", 100);

    @Override
    public void onEnable() {

        // 载入 config.yml 和 world.yml 文件
        saveDefaultConfig();
        saveResource("world.yml", false);
        saveResource("message.yml", false);
        try {
            watcher.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 初始化 world.yml 类
        WorldFile.INSTANCE.initialize(this);
        // 初始化 message.yml 类
        MessageFile.INSTANCE.initialize(this,Bukkit.getPluginManager().getPlugin("PlaceholderAPI"));

        // 注册 命令 和 Tab键 监听器
        Commands commandHandler = new Commands();
        this.getCommand("worldmanage").setExecutor(commandHandler);     // 命令
        this.getCommand("worldmanage").setTabCompleter(commandHandler); // Tab



        // 注册 点燃方块 监听器
        getServer().getPluginManager().registerEvents(new BlockIgnite(), this);
        // 注册 火焰传播 监听器
        getServer().getPluginManager().registerEvents(new FlameSpread(), this);
        // 注册 树叶自然衰减 监听器
        getServer().getPluginManager().registerEvents(new LeavesDecay(), this);
        // 注册 方块被破坏 监听器
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        // 注册 方块被放置 监听器
        getServer().getPluginManager().registerEvents(new BlockPlace(), this);
        // 注册 捡起物品 监听器
        getServer().getPluginManager().registerEvents(new ItemPickup(), this);
        // 注册 丢物品 监听器
        getServer().getPluginManager().registerEvents(new ItemDrop(), this);
        // 注册 使用桶 监听器
        getServer().getPluginManager().registerEvents(new PlayerBucketEmpty(), this);
        // 注册 饥饿度 监听器
        getServer().getPluginManager().registerEvents(new FoodLevelChange(), this);
        // 注册 玩家受伤 监听器
        getServer().getPluginManager().registerEvents(new PlayerDamage(), this);
        // 注册 玩家传送 监听器
        getServer().getPluginManager().registerEvents(new PlayerPortal(), this);
        // 注册 使用物品 监听器
        getServer().getPluginManager().registerEvents(new PlayerUseItem(), this);
        // 注册 右键实体交互 监听器
        getServer().getPluginManager().registerEvents(new PlayerInteractEntity(), this);
        // 注册 实体破坏方块 监听器
        getServer().getPluginManager().registerEvents(new EntityBlockBreak(), this);

        Bukkit.getConsoleSender().sendMessage("§b§l");
        Bukkit.getConsoleSender().sendMessage("§b§l");
        Bukkit.getConsoleSender().sendMessage("§b§l _       __           __    ____  ___                           ");
        Bukkit.getConsoleSender().sendMessage("§b§l| |     / /___  _____/ /___/ /  |/  /___ _____  ____ _____ ____ ");
        Bukkit.getConsoleSender().sendMessage("§b§l| | /| / / __ \\/ ___/ / __  / /|_/ / __ `/ __ \\/ __ `/ __ `/ _ \\");
        Bukkit.getConsoleSender().sendMessage("§b§l| |/ |/ / /_/ / /  / / /_/ / /  / / /_/ / / / / /_/ / /_/ /  __/");
        Bukkit.getConsoleSender().sendMessage("§b§l|__/|__/\\____/_/  /_/\\__,_/_/  /_/\\__,_/_/ /_/\\__,_/\\__, /\\___/ ");
        Bukkit.getConsoleSender().sendMessage("§b§l                                                   /____/       ");
        Bukkit.getConsoleSender().sendMessage("§b§l");
        Bukkit.getConsoleSender().sendMessage("§7Author: §aMaddyJace §7FeedbackEmail: §edixiaomai@qq.com §7FeedbackQQ: §e2743063754 §7Version: §e1.0");
        Bukkit.getConsoleSender().sendMessage("§f-------------------------------------------------------------------------------------");
        Bukkit.getConsoleSender().sendMessage("§b§l");
        Bukkit.getConsoleSender().sendMessage("§b§l");

    }

    @Override
    public void onDisable() {
        try {
            watcher.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
