package com.maddyjace.worldmanage.ListenerPlayerRules;

import com.maddyjace.worldmanage.ConfigFile.MessageFile;
import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

// 该类用于 阻止玩家受伤 和 击退
public class PlayerDamage implements Listener {

    // 取消受伤
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            World world = player.getWorld();
            if(WorldFile.INSTANCE.playerRules(world.getName(),"playerDamage", player)) {
                event.setCancelled(true);
                MessageFile getMessage = MessageFile.INSTANCE;
                if(getMessage.getMessage("PlayerDamageMessage") != null) {
                    MessageFile.parsePlaceholders(player, getMessage.getMessage("PluginsName") +
                            "&f: " + getMessage.getMessage("PlayerDamageMessage"));
                }
            }
        }
    }

    // 取消击退
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            World world = player.getWorld();
            if(WorldFile.INSTANCE.playerRules(world.getName(),"playerDamage", player)) {
                event.setCancelled(true);
            }
        }

    }

}
