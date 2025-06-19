package com.maddyjace.worldmanage;

import com.maddyjace.worldmanage.Listener.ProtectedAreas;
import org.bukkit.plugin.java.JavaPlugin;

public final class WorldManage extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ProtectedAreas(),  this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
