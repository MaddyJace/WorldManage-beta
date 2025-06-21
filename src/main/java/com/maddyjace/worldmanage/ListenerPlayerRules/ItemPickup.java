package com.maddyjace.worldmanage.ListenerPlayerRules;

import com.maddyjace.worldmanage.ConfigFile.MessageFile;
import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

// 该类监听玩家捡起物品
public class ItemPickup implements Listener {

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {

        Player player = (Player) event.getEntity();
        World world = player.getWorld();
        if(WorldFile.INSTANCE.playerRules(world.getName(),"itemPickup", player)) {
            event.setCancelled(true);
            MessageFile getMessage = MessageFile.INSTANCE;
            if(getMessage.getMessage("itemPickupMessage") != null) {
                MessageFile.parsePlaceholders(player, getMessage.getMessage("itemPickupMessage"));
            }
        }
    }
}
