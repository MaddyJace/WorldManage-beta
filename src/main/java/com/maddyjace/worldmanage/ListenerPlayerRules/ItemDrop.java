package com.maddyjace.worldmanage.ListenerPlayerRules;

import com.maddyjace.worldmanage.ConfigFile.MessageFile;
import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

// 该类当玩家扔物品时处理
public class ItemDrop implements Listener {

    // 存储每个玩家的提示冷却时间
    private final Set<UUID> cooldownPlayers = new HashSet<>();

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {

        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        World world = player.getWorld();
        if(WorldFile.INSTANCE.playerRules(world.getName(),"itemDrop", player)) {
            event.setCancelled(true);

            // 如果玩家在冷却中，直接跳过提示
            if (cooldownPlayers.contains(playerUUID)) return;

            MessageFile getMessage = MessageFile.INSTANCE;
            if(getMessage.getMessage("ItemDropMessage") != null) {
                MessageFile.parsePlaceholders(player, getMessage.getMessage("PluginsName") +
                        "&f: " + getMessage.getMessage("ItemDropMessage"));
            }

            // 添加到冷却列表
            cooldownPlayers.add(playerUUID);

            // 使用调度器在指定时间后移除冷却状态
            // 冷却时间（单位是 Tick，200 = 10秒）
            int cooldownTicks = 200;
            Bukkit.getScheduler().runTaskLater(
                    JavaPlugin.getProvidingPlugin(this.getClass()), // 获取插件实例
                    () -> cooldownPlayers.remove(playerUUID),
                    cooldownTicks
            );

        }

    }

}
