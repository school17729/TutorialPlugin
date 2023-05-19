package me.solarstar.listeners;

import me.solarstar.PlayerStatistics;
import me.solarstar.Tutorial;
import me.solarstar.items.StatisticsBookItem;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class JoinLeaveListener implements Listener {

    private final HashMap<String, PlayerStatistics> playerStatisticsMap;

    public JoinLeaveListener(HashMap<String, PlayerStatistics> playerStatisticsMap) {
        this.playerStatisticsMap = playerStatisticsMap;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        event.setJoinMessage("[Tutorial]: " + "A player " + ChatColor.YELLOW + playerName +  ChatColor.WHITE + " joined the server");

        initializePlayerStatistics(player);

        player.getInventory().setItem(8, (new StatisticsBookItem()).getItem());

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Health: 100"));
    }

    private void initializePlayerStatistics(Player player) {
        String playerName = player.getName();

        initializePlayerData(player);

        PlayerStatistics playerStatistics = new PlayerStatistics(playerName);
        playerStatistics.fromPlayerData(player);

        playerStatisticsMap.putIfAbsent(playerName, playerStatistics);
    }

    private void initializePlayerData(Player player) {
        initializePlayerDatum(player, "farmingPoints");
        initializePlayerDatum(player, "miningPoints");
        initializePlayerDatum(player, "loggingPoints");
        initializePlayerDatum(player, "combatPoints");
        initializePlayerDatum(player, "craftingPoints");
        initializePlayerDatum(player, "buildingPoints");
    }

    private void initializePlayerDatum(Player player, String key) {
        PersistentDataContainer container = player.getPersistentDataContainer();
        if (!container.has(new NamespacedKey(Tutorial.getPlugin(), key), PersistentDataType.INTEGER)) {
            container.set(new NamespacedKey(Tutorial.getPlugin(), key), PersistentDataType.INTEGER, 0);
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        event.setQuitMessage("[Tutorial]: " + "A player " + ChatColor.YELLOW + playerName +  ChatColor.WHITE + " left the server");

        setPlayerData(player);
    }

    private void setPlayerData(Player player) {
        String playerName = player.getName();
        PlayerStatistics playerStatistics = playerStatisticsMap.get(playerName);
        setPlayerDatum(player, "farmingPoints", playerStatistics.farming);
        setPlayerDatum(player, "miningPoints", playerStatistics.mining);
        setPlayerDatum(player, "loggingPoints", playerStatistics.logging);
        setPlayerDatum(player, "combatPoints", playerStatistics.combat);
        setPlayerDatum(player, "craftingPoints", playerStatistics.crafting);
        setPlayerDatum(player, "buildingPoints", playerStatistics.building);
    }

    private void setPlayerDatum(Player player, String key, int points) {
        PersistentDataContainer container = player.getPersistentDataContainer();
        container.set(new NamespacedKey(Tutorial.getPlugin(), key), PersistentDataType.INTEGER, points);
    }

    /*
    It doesn't really matter what the name of the event listener is
    onSolarStarLeaveBed works just as fine as onLeaveBed
     */
    @EventHandler
    public void onPlayerLeaveBed(PlayerBedLeaveEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        player.sendMessage("[Tutorial]: " + "A player " + ChatColor.YELLOW + playerName +  ChatColor.WHITE + " left a bed");
    }
}
