package me.solarstar.commands;

import me.solarstar.PlayerStatistics;
import me.solarstar.holders.StatisticsMenuHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class GetStatisticsCommand implements CommandExecutor {

    private final HashMap<String, PlayerStatistics> playerStatisticsMap;

    public GetStatisticsCommand(HashMap<String, PlayerStatistics> playerStatisticsMap) {
        this.playerStatisticsMap = playerStatisticsMap;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        String playerName = player.getName();
        PlayerStatistics playerStatistics = playerStatisticsMap.get(playerName);

        Inventory inventory = (new StatisticsMenuHolder(player, playerStatistics)).getInventory();
        player.openInventory(inventory);
        return true;
    }
}
