package com.maddyjace.worldmanage.ListenerPlayerRules;

import com.maddyjace.worldmanage.ConfigFile.MessageFile;
import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDrop implements Listener {
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {

        Player player = event.getPlayer();
        World world = player.getWorld();
        if(WorldFile.INSTANCE.playerRules(world.getName(),"itemDrop", player)) {
            event.setCancelled(true);
            MessageFile getMessage = MessageFile.INSTANCE;
            if(getMessage.getMessage("itemDropMessage") != null) {
                MessageFile.parsePlaceholders(player, getMessage.getMessage("itemDropMessage"));
            }

        }

    }

}
