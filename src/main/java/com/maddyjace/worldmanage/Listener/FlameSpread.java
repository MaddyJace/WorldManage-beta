package com.maddyjace.worldmanage.Listener;

import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockSpreadEvent;

// 火焰传播
public class FlameSpread implements Listener {

    @EventHandler
    public void onBlockSpread(BlockSpreadEvent event) {
        World world = event.getBlock().getWorld();
        // 源是火焰，就取消（无论目标是什么）
        if (event.getSource().getType() == Material.FIRE) {
            if(WorldFile.INSTANCE.globalRules(world.getName(),"flameSpread")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        World world = event.getBlock().getWorld();
        if(WorldFile.INSTANCE.globalRules(world.getName(),"flameSpread")) {
            // 防止方块被烧掉
            event.setCancelled(true);
        }
    }

}
