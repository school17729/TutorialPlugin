package me.solarstar.listeners;

import me.solarstar.holders.StatisticsMenuHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuClickListener implements Listener {

    public MenuClickListener() {}

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof StatisticsMenuHolder) {
            event.setCancelled(true);
        }
    }
}
