package me.solarstar.items;

import me.solarstar.Tutorial;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class StatisticsBookItem {

    private final ItemStack item;

    public StatisticsBookItem() {
        item = new ItemStack(Material.BOOK, 1);
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container;
        String displayName = ChatColor.RESET + "" + ChatColor.GREEN + "Statistics Book";
        if (itemMeta != null) {
            container = itemMeta.getPersistentDataContainer();
            container.set(new NamespacedKey(Tutorial.getPlugin(), "type"), PersistentDataType.STRING, "statisticsBook");
            itemMeta.setDisplayName(displayName);
        }
        item.setItemMeta(itemMeta);
    }

    public ItemStack getItem() {
        return item;
    }

}
