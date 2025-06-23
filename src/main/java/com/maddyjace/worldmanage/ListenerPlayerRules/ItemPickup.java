package com.maddyjace.worldmanage.ListenerPlayerRules;

import com.maddyjace.worldmanage.ConfigFile.MessageFile;
import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import java.util.*;

// 该类监听玩家捡起物品
public class ItemPickup implements Listener {


    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {

        Player player = (Player) event.getEntity();
        UUID playerId = player.getUniqueId();
        World world = player.getWorld();
        if(WorldFile.INSTANCE.playerRules(world.getName(),"itemPickup", player)) {
            event.setCancelled(true);
            // 取消事件后向玩家发送提示信息
            if(MessageFile.getMessage("ItemPickupMessage") != null) {
                MessageFile.parsePlaceholders(player, MessageFile.getMessage("ItemPickupMessage"));
            }

        }
    }
}
