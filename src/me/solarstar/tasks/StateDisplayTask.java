package me.solarstar.tasks;

import me.solarstar.Tutorial;
import org.bukkit.scheduler.BukkitRunnable;

public class StateDisplayTask extends BukkitRunnable {

    final Tutorial plugin;

    public StateDisplayTask(Tutorial plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        System.out.println("It ran!");
    }

}
