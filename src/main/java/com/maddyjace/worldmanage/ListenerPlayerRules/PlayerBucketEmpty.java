package com.maddyjace.worldmanage.ListenerPlayerRules;

import com.maddyjace.worldmanage.ConfigFile.MessageFile;
import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

// 该类阻止 使用任何通 和 任何空通获取液体
public class PlayerBucketEmpty implements Listener {

    // 阻止所有"xxx_BUCKET"类型的桶
    @EventHandler
    public void onBucketUse(PlayerBucketEmptyEvent event) {

        Player player = event.getPlayer();
        World world = player.getWorld();
        if(WorldFile.INSTANCE.playerRules(world.getName(),"usePail", player)) {
            Material bucket = event.getBucket();
            if (bucket.name().endsWith("_BUCKET")) {
                event.setCancelled(true);
                MessageFile getMessage = MessageFile.INSTANCE;
                if(getMessage.getMessage("usePailMessage") != null) {
                    MessageFile.parsePlaceholders(player, getMessage.getMessage("usePailMessage"));
                }
            }
        }

    }

    // 防止玩家获得液体
    @EventHandler
    public void onBucketFill(PlayerBucketFillEvent event) {

        Player player = event.getPlayer();
        World world = player.getWorld();
        if(WorldFile.INSTANCE.playerRules(world.getName(),"usePail", player)) {
            event.setCancelled(true);
            MessageFile getMessage = MessageFile.INSTANCE;
            if(getMessage.getMessage("usePailMessage") != null) {
                MessageFile.parsePlaceholders(player, getMessage.getMessage("usePailMessage"));
            }
        }

    }

}
