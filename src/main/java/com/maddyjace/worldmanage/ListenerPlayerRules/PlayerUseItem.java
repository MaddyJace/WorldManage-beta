package com.maddyjace.worldmanage.ListenerPlayerRules;

import com.maddyjace.worldmanage.ConfigFile.MessageFile;
import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

// 该类用于阻止玩家使用物品
public class PlayerUseItem implements Listener {

    @EventHandler
    public void onPlayerUseItem(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        // 判断玩家手上是否有物品
        if(WorldFile.INSTANCE.playerRules(world.getName(),"playerUseItem", player)) {
            if (event.getItem() != null) {
                event.setCancelled(true);
                MessageFile getMessage = MessageFile.INSTANCE;
                if(getMessage.getMessage("playerUseItem") != null) {
                    MessageFile.parsePlaceholders(player, getMessage.getMessage("playerUseItemMessage"));
                }
            }
        }
    }

}
