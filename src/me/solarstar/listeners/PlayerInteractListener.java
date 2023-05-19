package me.solarstar.listeners;

import me.solarstar.PlayerStatistics;
import me.solarstar.Tutorial;
import me.solarstar.holders.StatisticsMenuHolder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class PlayerInteractListener implements Listener {

    private final HashMap<String, PlayerStatistics> playerStatisticsMap;

    public PlayerInteractListener(HashMap<String, PlayerStatistics> playerStatisticsMap) {
        this.playerStatisticsMap = playerStatisticsMap;
    }

    @EventHandler
    public void onItemInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        PlayerStatistics playerStatistics = playerStatisticsMap.get(playerName);
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        ItemMeta heldItemMeta = heldItem.getItemMeta();
        String heldItemType = "";

        Action action = event.getAction();
        boolean rightClick = action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK);
        if (heldItemMeta != null) {
            PersistentDataContainer container = heldItemMeta.getPersistentDataContainer();
            heldItemType = container.get(new NamespacedKey(Tutorial.getPlugin(), "type"), PersistentDataType.STRING);
        }
        if (heldItemType != null) {
            if (heldItemType.equals("statisticsBook") && rightClick) {
                Inventory inventory = (new StatisticsMenuHolder(player, playerStatistics)).getInventory();
                player.openInventory(inventory);
            }
        }

        if (action.equals(Action.RIGHT_CLICK_AIR)) {
            System.out.println("I do my right clicks in the air sometimes");
        }
    }
}
