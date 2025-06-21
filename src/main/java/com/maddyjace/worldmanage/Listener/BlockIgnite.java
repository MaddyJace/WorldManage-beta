package com.maddyjace.worldmanage.Listener;

import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

public class BlockIgnite implements Listener {

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {

        World world = event.getBlock().getWorld();
        if(WorldFile.INSTANCE.globalRules(world.getName(),"blockIgnite")) {
            Player player = event.getPlayer();
            if(player != null && !WorldFile.INSTANCE.playerRules(world.getName(),"blockIgnite", player)) {
                return;
            }
            event.setCancelled(true);
        }

    }

}
