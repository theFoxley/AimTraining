package me.foxley.aimtraining;

import me.foxley.aimtraining.training.TrainingInterface;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AimTraining extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getWorlds().get(0).setAutoSave(false);
        Bukkit.getPluginManager().registerEvents(new EventsManager(this), this);
    }

    public void setPlayerInSpawn(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());

        player.getInventory().clear();
        player.setGameMode(GameMode.ADVENTURE);
        player.teleport(player.getWorld().getSpawnLocation().add(0.5, 0, 0.5));
        player.getInventory().setItem(4, TrainingInterface.getItem());

        for(Player p : Bukkit.getOnlinePlayers()) {
            player.showPlayer(p);
        }
    }
}
