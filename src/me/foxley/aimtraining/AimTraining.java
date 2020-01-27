package me.foxley.aimtraining;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AimTraining extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        Bukkit.getPluginManager().registerEvents(new EventsManager(this), this);
    }

    public void setPlayerInSpawn(Player player) {
        player.setGameMode(GameMode.ADVENTURE);
        player.teleport(player.getWorld().getSpawnLocation());

    }

    public boolean isInSpawn(Player player) {
        return player.getGameMode() == GameMode.ADVENTURE;
    }
}
