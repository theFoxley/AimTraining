package me.foxley.aimtraining.training.modes;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.Random;

public class FlickingMode implements Mode {

    private byte distance;

    public FlickingMode(byte distance_) {
        this.distance = distance_;
    }

    @Override
    public Location getNextLocation(Location source) {
        double yaw = source.getYaw();
        double pitch = source.getPitch();
        Random random = new Random();
        yaw = yaw + 90 * random.nextDouble() - 45;
        pitch = (MAX_PITCH-MIN_PITCH) * random.nextDouble() + MIN_PITCH;
        double angleXZ = Math.PI * yaw / 180;
        double angleY = Math.PI * pitch / 180;

        Vector direction = new Vector(-Math.sin(angleXZ), -Math.sin(angleY), Math.cos(angleXZ)).multiply(distance);

        return source.clone().add(direction);
    }
}
