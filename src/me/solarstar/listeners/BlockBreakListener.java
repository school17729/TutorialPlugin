package me.solarstar.listeners;

import me.solarstar.PlayerStatistics;
import me.solarstar.Tutorial;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class BlockBreakListener implements Listener {

    private final HashMap<String, PlayerStatistics> playerStatisticsMap;

    public BlockBreakListener(HashMap<String, PlayerStatistics> playerStatisticsMap) {
        this.playerStatisticsMap = playerStatisticsMap;
    }

    private int getFarmingPoints(Block block) {
        Material type = block.getType();
        int farmingPoints = 0;
        boolean isAgeableCrop = false;
        if (type == Material.WHEAT) {
            farmingPoints = 10;
            isAgeableCrop = true;
        } else if (type == Material.CARROTS || type == Material.POTATOES || type == Material.BEETROOTS) {
            farmingPoints = 20;
            isAgeableCrop = true;
        }

        if (isAgeableCrop) {
            Ageable ageable = (Ageable) block.getBlockData();
            if (ageable.getAge() == ageable.getMaximumAge()) {
                return farmingPoints;
            } else {
                return 0;
            }
        }
        return 0;
    }

    private int getMiningPoints(Block block) {
        Material type = block.getType();
        int miningPoints = 0;
        if (type == Material.DIRT || type == Material.GRASS_BLOCK) {
            miningPoints = 10;
        } else if (type == Material.STONE || type == Material.ANDESITE || type == Material.DIORITE || type == Material.GRANITE) {
            miningPoints = 20;
        } else if (type == Material.GRAVEL || type == Material.COAL_ORE) {
            miningPoints = 30;
        } else if (type == Material.IRON_ORE || type == Material.GOLD_ORE || type == Material.REDSTONE_ORE || type == Material.LAPIS_ORE) {
            miningPoints = 40;
        } else if (type == Material.DIAMOND_ORE || type == Material.EMERALD_ORE) {
            miningPoints = 50;
        }

        return miningPoints;
    }

    private int getLoggingPoints(Block block) {
        Material type = block.getType();
        int loggingPoints = 0;
        if (type == Material.OAK_LOG || type == Material.BIRCH_LOG || type == Material.SPRUCE_LOG) {
            loggingPoints = 10;
        } else if (type == Material.ACACIA_LOG || type == Material.JUNGLE_LOG || type == Material.DARK_OAK_LOG) {
            loggingPoints = 20;
        } else if (type == Material.MANGROVE_LOG || type == Material.CRIMSON_STEM || type == Material.WARPED_STEM) {
            loggingPoints = 30;
        }

        return loggingPoints;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        PlayerStatistics playerStatistics = playerStatisticsMap.get(playerName);
        Block block = event.getBlock();
        int farmingPoints = getFarmingPoints(block);
        playerStatistics.farming += farmingPoints;
        if (farmingPoints > 0) {
            sendPoints(player, "Farming", farmingPoints);
        }

        int miningPoints = getMiningPoints(block);
        playerStatistics.mining += miningPoints;
        if (miningPoints > 0) {
            sendPoints(player, "Mining", miningPoints);
        }

        int loggingPoints = getLoggingPoints(block);
        playerStatistics.logging += loggingPoints;
        if (loggingPoints > 0) {
            sendPoints(player, "Logging", loggingPoints);
        }
    }



    private void sendPoints(Player player, String statisticType, int points) {
        String playerName = player.getName();
        player.sendMessage("Player " + ChatColor.YELLOW + playerName + ChatColor.WHITE + ": +" + points + " " + statisticType + " Points");
    }
}
