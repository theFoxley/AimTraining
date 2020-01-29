package me.foxley.aimtraining.training;

import me.foxley.aimtraining.AimTraining;
import me.foxley.aimtraining.training.modes.Mode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class TrainingManager {

    private AimTraining aimTraining;
    private HashMap<UUID, Training> trainings;

    public TrainingManager(AimTraining aimTraining_) {
        this.aimTraining = aimTraining_;
        this.trainings = new HashMap<UUID, Training>(5);
    }

    public void registerTraining(Player player, Mode mode) {
        Location location = player.getWorld().getSpawnLocation().clone().add(10, 0, 0);
        trainings.put(player.getUniqueId(), new Training(this, player, mode, location));
    }

    public void unregisterTraining(Player player) {
        trainings.get(player.getUniqueId()).unregister();
        trainings.remove(player.getUniqueId());
        aimTraining.setPlayerInSpawn(player);
    }

    public void playerInteract(Player player) {
        trainings.get(player.getUniqueId()).onInteract();
    }

    public boolean isTraining(Player player) {
        return trainings.containsKey(player.getUniqueId());
    }
}
