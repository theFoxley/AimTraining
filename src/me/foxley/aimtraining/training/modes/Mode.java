package me.foxley.aimtraining.training.modes;

import org.bukkit.Location;

public interface Mode {

    public static byte MIN_PITCH = -30;
    public static byte MAX_PITCH = 15;

    /*
    Pitch to location must be between -30 and 20
     */

    public Location getNextLocation(Location source);
}
