package me.foxley.aimtraining.training.generator;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.Random;

public class ReachGenerator implements Generator {

    @Override
    public Vector getNextLocation(Location source) {
        double yaw = source.getYaw();
        double pitch = source.getPitch();
        Random random = new Random();
        yaw = yaw + 90 * random.nextDouble() - 45;
        pitch = 60 * random.nextDouble() - 40; // Entre -40 et 20
        double angleXZ = Math.PI * yaw / 180;
        double angleY = Math.PI * pitch / 180;

        Vector direction = new Vector(-Math.sin(angleXZ), -Math.sin(angleY), Math.cos(angleXZ)).multiply(3);

        return direction.add(source.toVector());
    }
}
