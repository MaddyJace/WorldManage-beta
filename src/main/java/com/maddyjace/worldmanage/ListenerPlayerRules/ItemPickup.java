package com.maddyjace.worldmanage.ListenerPlayerRules;

import com.maddyjace.worldmanage.ConfigFile.MessageFile;
import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

// 该类监听玩家捡起物品
public class ItemPickup implements Listener {

    // 存储每个玩家的提示冷却时间
    private final Set<UUID> cooldownPlayers = new HashSet<>();

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {

        Player player = (Player) event.getEntity();
        UUID playerId = player.getUniqueId();
        World world = player.getWorld();
        if(WorldFile.INSTANCE.playerRules(world.getName(),"itemPickup", player)) {
            event.setCancelled(true);


            // 如果玩家在冷却中，直接跳过提示
            if (cooldownPlayers.contains(playerId)) return;



            MessageFile getMessage = MessageFile.INSTANCE;
            if(getMessage.getMessage("ItemPickupMessage") != null) {
                MessageFile.parsePlaceholders(player, getMessage.getMessage("PluginsName") +
                        "&f: " + getMessage.getMessage("ItemPickupMessage"));
            }

            // 添加到冷却列表
            cooldownPlayers.add(playerId);

            // 使用调度器在指定时间后移除冷却状态
            // 冷却时间（单位是 Tick，200 = 10秒）
            int cooldownTicks = 200;
            Bukkit.getScheduler().runTaskLater(
                    JavaPlugin.getProvidingPlugin(this.getClass()), // 获取插件实例
                    () -> cooldownPlayers.remove(playerId),
                    cooldownTicks
            );

        }
    }
}
