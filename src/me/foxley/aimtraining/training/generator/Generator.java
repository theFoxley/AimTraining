package me.foxley.aimtraining.training.generator;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public interface Generator {

    /*
    Pitch to location must be between -40 and 20
     */

    public Vector getNextLocation(Location source);
}
