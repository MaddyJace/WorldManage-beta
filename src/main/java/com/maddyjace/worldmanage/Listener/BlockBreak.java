package com.maddyjace.worldmanage.Listener;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

// 该类是监听器，监听实体破坏方块
public class BlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        World world = event.getBlock().getWorld();
        event.setCancelled(true);

//        Player player = event.getPlayer();
//
//        player.sendMessage("§c你不能破坏方块！");
    }

}
