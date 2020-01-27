package me.foxley.aimtraining.training;

import me.foxley.aimtraining.training.generator.ReachGenerator;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class TrainingManager {

    private HashMap<UUID, Training> trainings;

    public TrainingManager() {
        this.trainings = new HashMap<UUID, Training>(5);
    }

    public void addPlayer(Player player) {
        trainings.put(player.getUniqueId(), new Training(player, new ReachGenerator(), player.getLocation()));
    }

    public void removePlayer(Player player) {
        trainings.remove(player.getUniqueId());
    }

    public void playerInteract(Player player) {
        trainings.get(player.getUniqueId()).onInteract();
    }
}
