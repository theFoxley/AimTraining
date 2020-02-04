package me.foxley.aimtraining.training;

import me.foxley.aimtraining.AimTraining;
import me.foxley.aimtraining.training.tasks.TrainingTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class TrainingManager {

    private AimTraining aimTraining;
    private HashMap<UUID, Training> trainings;
    private Location location;

    public TrainingManager(AimTraining aimTraining_) {
        this.aimTraining = aimTraining_;
        this.trainings = new HashMap<UUID, Training>(5);
        this.location = new Location(Bukkit.getWorlds().get(0), 0.5, 18, 0.5, 0, 0);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(aimTraining, new Runnable() {
            @Override
            public void run() {
                Iterator<Map.Entry<UUID, Training>> iterator = trainings.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<UUID, Training> entry = iterator.next();
                    if (entry.getValue().onSecondClock()) {
                        entry.getValue().unregister();
                        iterator.remove();
                    }
                }
            }
        }, 20, 20);
    }

    public void registerTraining(Player player, TrainingTask mode) {
        trainings.put(player.getUniqueId(), new Training(this, player, mode, location));
    }

    public void unregisterTraining(UUID uuid) {
        trainings.get(uuid).unregister();
        trainings.remove(uuid);
    }

    public void setPlayerInSpawn(Player player) {
        aimTraining.setPlayerInSpawn(player);
    }

    public void playerInteract(Player player) {
        trainings.get(player.getUniqueId()).onInteract();
    }

    public boolean isTraining(Player player) {
        return trainings.containsKey(player.getUniqueId());
    }
}
