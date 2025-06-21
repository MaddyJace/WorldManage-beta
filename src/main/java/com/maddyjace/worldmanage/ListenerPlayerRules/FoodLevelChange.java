package com.maddyjace.worldmanage.ListenerPlayerRules;

import com.maddyjace.worldmanage.ConfigFile.MessageFile;
import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

// 取消饥饿度减少事件
public class FoodLevelChange implements Listener {

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            World world = player.getWorld();
            if(WorldFile.INSTANCE.playerRules(world.getName(),"food", player)) {
                event.setCancelled(true);
                MessageFile getMessage = MessageFile.INSTANCE;
                if(getMessage.getMessage("foodMessage") != null) {
                    MessageFile.parsePlaceholders(player, getMessage.getMessage("foodMessage"));
                }
            }
            if(WorldFile.INSTANCE.playerRules(world.getName(),"foodSatiety", player)) {
                player.setFoodLevel(20);
                MessageFile getMessage = MessageFile.INSTANCE;
                if(getMessage.getMessage("foodSatietyMessage") != null) {
                    MessageFile.parsePlaceholders(player, getMessage.getMessage("foodSatietyMessage"));
                }
            }
        }
    }
}
