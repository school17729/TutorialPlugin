package me.solarstar;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerStatistics {
    public String playerName;
    public int farming;
    public int mining;
    public int logging;
    public int combat;
    public int crafting;
    public int building;

    public PlayerStatistics(String playerName) {
        this.playerName = playerName;

        farming = 0;
        mining = 0;
        logging = 0;
        combat = 0;
        crafting = 0;
        building = 0;
    }

    public void fromPlayerData(Player player) {
        PersistentDataContainer container = player.getPersistentDataContainer();
        Integer farmingInteger = container.get(new NamespacedKey(Tutorial.getPlugin(), "farmingPoints"), PersistentDataType.INTEGER);
        if (farmingInteger != null) {
            farming = farmingInteger;
        }
        Integer miningInteger = container.get(new NamespacedKey(Tutorial.getPlugin(), "miningPoints"), PersistentDataType.INTEGER);
        if (miningInteger != null) {
            mining = miningInteger;
        }
        Integer loggingInteger = container.get(new NamespacedKey(Tutorial.getPlugin(), "loggingPoints"), PersistentDataType.INTEGER);
        if (loggingInteger != null) {
            logging = loggingInteger;
        }
        Integer combatInteger = container.get(new NamespacedKey(Tutorial.getPlugin(), "combatPoints"), PersistentDataType.INTEGER);
        if (combatInteger != null) {
            combat = combatInteger;
        }
        Integer craftingInteger = container.get(new NamespacedKey(Tutorial.getPlugin(), "craftingPoints"), PersistentDataType.INTEGER);
        if (craftingInteger != null) {
            crafting = craftingInteger;
        }
        Integer buildingInteger = container.get(new NamespacedKey(Tutorial.getPlugin(), "buildingPoints"), PersistentDataType.INTEGER);
        if (buildingInteger != null) {
            building = buildingInteger;
        }
    }
}
