package com.maddyjace.worldmanage.ListenerGlobalRules;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;

@SuppressWarnings("ALL")
public class ProtectedAreas implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        Location location = event.getBlock().getLocation();
        Location location2 = new Location(world, 0, 88 ,0);

        if(isInRange(location, location2, 5)) {
            System.out.println("toString: " + location.toString());
            event.setCancelled(true);
        }

        // 向控制台输出玩家名和破坏的方块类型
        String playerName = event.getPlayer().getName();
        String blockType = event.getBlock().getType().toString();

        System.out.println(playerName + " 破坏了一个方块：" + blockType);

    }


    public static boolean isInRange(Location center, Location current, int radius) {
        if (center == null || current == null) return false;
        if (!center.getWorld().equals(current.getWorld())) return false;

        double dx = current.getX() - center.getX();
        double dz = current.getZ() - center.getZ();

        return dx * dx + dz * dz <= radius * radius;
    }

}
