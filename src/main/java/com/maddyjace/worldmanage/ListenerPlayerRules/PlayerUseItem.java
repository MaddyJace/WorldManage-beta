package com.maddyjace.worldmanage.ListenerPlayerRules;

import com.maddyjace.worldmanage.ConfigFile.MessageFile;
import com.maddyjace.worldmanage.ConfigFile.WorldFile;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

// 该类用于阻止玩家使用物品
public class PlayerUseItem implements Listener {

    // 当玩家使用物品时处理
    @EventHandler
    public void onPlayerUseItem(PlayerInteractEvent event) {

        Player player = event.getPlayer(); // 获取玩家类型
        World world = player.getWorld();   // 获取玩家所在世界类型
        ItemStack item = event.getItem();  // 获取物品堆叠
        if(item != null) { // 该对象不能是空的
            String itemName = String.valueOf(item.getType()); // 获取物品常量名称
            if(WorldFile.INSTANCE.playerRulesList(world.getName(), "playerRules", player,
                    "playerUseItem", itemName)) {
                event.setCancelled(true);

                MessageFile getMessage = MessageFile.INSTANCE;
                if(getMessage.getMessage("PlayerUseItemMessage") != null) {
                    MessageFile.parsePlaceholders(player, getMessage.getMessage("PlayerUseItemMessage"));
                }


            }
        }

    }

    // 当玩家与方块交互时
    @EventHandler
    public void onPlayerUseItem01(PlayerInteractEvent event) {

        // 当玩家是右键点击时处理
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            // 右键点击的方块不能为空(如: 空气)
            if (event.getClickedBlock() != null) {
                Player player = event.getPlayer(); // 获取玩家类型
                World world = player.getWorld();   // 获取玩家所在世界类型
                String blockName = event.getClickedBlock().getType().toString(); // 获取玩家右键点击的方块常量名称
                if(WorldFile.INSTANCE.playerRulesList(world.getName(), "playerRules", player,
                        "PlayerInteractBlock", blockName)) {
                    event.setCancelled(true);

                    MessageFile getMessage = MessageFile.INSTANCE;
                    if(getMessage.getMessage("PlayerInteractBlockMessage") != null) {
                        MessageFile.parsePlaceholders(player, getMessage.getMessage("PlayerInteractBlockMessage"));
                    }


                }
            }
        }

    }


    @EventHandler
    public void onPlayerUseItem02(PlayerInteractEvent event) {

        if (event.getAction() == Action.PHYSICAL) {
            Player player = event.getPlayer(); // 获取玩家类型
            World world = player.getWorld();   // 获取玩家所在世界类型
            String blockName = event.getClickedBlock() != null ? event.getClickedBlock().getType().toString() : null;
            if(blockName != null) {
                if(WorldFile.INSTANCE.playerRulesList(world.getName(), "playerRules", player,
                        "PlayerTriggerBlock", blockName)) {
                    event.setCancelled(true);
                }

                MessageFile getMessage = MessageFile.INSTANCE;
                if(getMessage.getMessage("PlayerTriggerBlockMessage") != null) {
                    MessageFile.parsePlaceholders(player, getMessage.getMessage("PlayerTriggerBlockMessage"));
                }


            }
        }

    }

}
