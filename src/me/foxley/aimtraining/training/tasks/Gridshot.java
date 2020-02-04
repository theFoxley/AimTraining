package me.foxley.aimtraining.training.tasks;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.Random;

public class Gridshot extends TrainingTask {

    public Gridshot(byte distance_) {
        super(distance_, (byte) 3);
    }

    @Override
    protected Vector getNextTargetDirection(Location source) {
        Random random = new Random();
        double yaw = 40 * random.nextDouble() - 20;
        double pitch = 20 * random.nextDouble() - 10; // De -10 à 30
        double angleXZ = Math.PI * yaw / 180;
        double angleY = Math.PI * pitch / 180;

        Vector direction = new Vector(-Math.sin(angleXZ), -Math.sin(angleY), Math.cos(angleXZ));
        return direction;
    }
}
