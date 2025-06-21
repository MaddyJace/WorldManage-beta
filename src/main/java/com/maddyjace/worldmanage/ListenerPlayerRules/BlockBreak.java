package com.maddyjace.worldmanage.ListenerPlayerRules;

import com.maddyjace.worldmanage.ConfigFile.MessageFile;
import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

// 该类是监听器，监听玩家破坏方块
public class BlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        World world = event.getBlock().getWorld();
        if(WorldFile.INSTANCE.playerRules(world.getName(),"blockBreak", player)) {
            MessageFile getMessage = MessageFile.INSTANCE;
            if(getMessage.getMessage("blockBreakMessage") != null) {

            }
            MessageFile.parsePlaceholders(player, getMessage.getMessage("blockBreakMessage"));
            event.setCancelled(true);
        }

    }

}
