package me.solarstar;

import me.solarstar.commands.GetStatisticsCommand;
import me.solarstar.listeners.BlockBreakListener;
import me.solarstar.listeners.JoinLeaveListener;
import me.solarstar.listeners.PlayerInteractListener;
import me.solarstar.listeners.MenuClickListener;
import me.solarstar.tasks.StateDisplayTask;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Objects;

public final class Tutorial extends JavaPlugin implements Listener {

    private static Tutorial plugin;
    private HashMap<String, PlayerStatistics> playerStatisticsMap;

    public static Tutorial getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        System.out.println("[Tutorial]: " + "Tutorial started");
        playerStatisticsMap = new HashMap<>();

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new JoinLeaveListener(playerStatisticsMap), this);
        pluginManager.registerEvents(new BlockBreakListener(playerStatisticsMap), this);
        pluginManager.registerEvents(new MenuClickListener(), this);
        pluginManager.registerEvents(new PlayerInteractListener(playerStatisticsMap), this);

        Objects.requireNonNull(getCommand("getstatistics")).setExecutor(new GetStatisticsCommand(playerStatisticsMap));

    }

    @Override
    public void onDisable() {
        System.out.println("[Tutorial]: " + "Tutorial finished");
    }
}
