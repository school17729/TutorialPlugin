package me.solarstar.holders;

import me.solarstar.PlayerStatistics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;

public class StatisticsMenuHolder implements InventoryHolder {

    private final Player player;
    private final PlayerStatistics playerStatistics;
    private final String inventoryTitle;
    private final Inventory inventory;

    public StatisticsMenuHolder(Player player, PlayerStatistics playerStatistics) {
        this.player = player;
        this.playerStatistics = playerStatistics;

        String playerName = player.getName();
        inventoryTitle = ChatColor.YELLOW + playerName + ChatColor.WHITE + "'s Statistics";
        inventory = Bukkit.createInventory(this, 54, inventoryTitle);

        String farmingText = ChatColor.RESET + "Farming: " + playerStatistics.farming + " Points";
        inventory.setItem(10, createItem(Material.HAY_BLOCK, farmingText));

        String miningText = ChatColor.RESET + "Mining: " + playerStatistics.mining + " Points";
        inventory.setItem(11, createItem(Material.COBBLESTONE, miningText));

        String loggingText = ChatColor.RESET + "Logging: " + playerStatistics.logging + " Points";
        inventory.setItem(12, createItem(Material.OAK_LOG, loggingText));

        String combatText = ChatColor.RESET + "Combat: " + playerStatistics.combat + " Points";
        inventory.setItem(13, createItem(Material.IRON_SWORD, combatText));

        String craftingText = ChatColor.RESET + "Crafting: " + playerStatistics.crafting + " Points";
        inventory.setItem(14, createItem(Material.CRAFTING_TABLE, craftingText));

        String buildingText = ChatColor.RESET + "Building: " + playerStatistics.building + " Points";
        inventory.setItem(15, createItem(Material.BRICKS, buildingText));
    }

    public ItemStack createItem(Material material, String displayName) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta != null) {
            itemMeta.setDisplayName(displayName);
        }
        item.setItemMeta(itemMeta);
        return item;
    }

    @Override
    @Nonnull
    public Inventory getInventory() {
        return inventory;
    }
}
