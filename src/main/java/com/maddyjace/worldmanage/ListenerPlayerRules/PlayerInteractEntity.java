package com.maddyjace.worldmanage.ListenerPlayerRules;

import com.maddyjace.worldmanage.ConfigFile.MessageFile;
import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

// 该类阻止玩家与实体交互
public class PlayerInteractEntity implements Listener {

    @SuppressWarnings("ALL")
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        Entity entity = event.getRightClicked();
        String entityName = entity.getType().toString().toLowerCase();

        if(WorldFile.INSTANCE.playerRulesList(world.getName(), "playerRules", player,
                "PlayerInteractEntity", entityName)) {
            event.setCancelled(true);

        }

    }

    @SuppressWarnings("ALL")
    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        Entity entity = event.getRightClicked();
        String entityName = entity.getType().toString().toLowerCase();

        if(WorldFile.INSTANCE.playerRulesList(world.getName(), "playerRules", player,
                "PlayerInteractEntity", entityName)) {
            event.setCancelled(true);
            MessageFile getMessage = MessageFile.INSTANCE;
            if(getMessage.getMessage("PlayerInteractEntityMessage") != null) {
                MessageFile.parsePlaceholders(player, getMessage.getMessage("PluginsName") +
                        "&f: " + getMessage.getMessage("PlayerInteractEntityMessage"));
            }
        }
    }


}
