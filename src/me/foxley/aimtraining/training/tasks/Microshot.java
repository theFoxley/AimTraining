package me.foxley.aimtraining.training.tasks;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.Random;

public class Microshot extends TrainingTask {

    public Microshot(byte distance_) {
        super(distance_, (byte) 1);
    }

    @Override
    protected Vector getNextTargetDirection(Location source) {
        Random random = new Random();
        double yaw = 20 * random.nextDouble() - 10;
        double pitch = 20 * random.nextDouble() - 10;
        double angleXZ = Math.PI * yaw / 180;
        double angleY = Math.PI * pitch / 180;

        Vector direction = new Vector(-Math.sin(angleXZ), -Math.sin(angleY), Math.cos(angleXZ)).normalize();
        return direction;
    }
}
