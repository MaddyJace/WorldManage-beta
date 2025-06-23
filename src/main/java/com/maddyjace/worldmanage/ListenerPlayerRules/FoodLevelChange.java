package com.maddyjace.worldmanage.ListenerPlayerRules;

import com.maddyjace.worldmanage.ConfigFile.MessageFile;
import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

// 取消饥饿度减少事件
public class FoodLevelChange implements Listener {

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            World world = player.getWorld();
            if(WorldFile.INSTANCE.playerRules(world.getName(),"food", player)) {
                event.setCancelled(true);
                // 取消事件后向玩家发送提示信息
                if(MessageFile.getMessage("FoodLevelChangeMessage") != null) {
                    MessageFile.parsePlaceholders(player, MessageFile.getMessage("FoodLevelChangeMessage"));
                }
            }



            if(WorldFile.INSTANCE.playerRules(world.getName(),"foodSatiety", player)) {
                Bukkit.getScheduler().runTaskLater(
                        JavaPlugin.getProvidingPlugin(this.getClass()),
                        () -> player.setFoodLevel(20), 1L // 延迟1tick确保事件已处理完
                );
                // 取消事件后向玩家发送提示信息
                if(MessageFile.getMessage("FoodSatietyMessage") != null) {
                    MessageFile.parsePlaceholders(player, MessageFile.getMessage("FoodSatietyMessage"));
                }
            }
        }


    }
}
