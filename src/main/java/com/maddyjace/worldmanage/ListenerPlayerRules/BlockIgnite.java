package com.maddyjace.worldmanage.ListenerPlayerRules;

import com.maddyjace.worldmanage.ConfigFile.MessageFile;
import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

// 该类监听实体点燃方块
public class BlockIgnite implements Listener {

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        Player player = event.getPlayer();
        World world = event.getBlock().getWorld();
        if(WorldFile.INSTANCE.globalRules(world.getName(),"blockIgnite")) {
            if(player != null && !WorldFile.INSTANCE.playerRules(world.getName(),"blockIgnite", player)) {
                return;
            }
            event.setCancelled(true);

            MessageFile getMessage = MessageFile.INSTANCE;
            if(getMessage.getMessage("BlockIgniteMessage") != null) {
                MessageFile.parsePlaceholders(player, getMessage.getMessage("PluginsName") +
                        "&f: " + getMessage.getMessage("BlockIgniteMessage"));
            }

        }

    }

}
