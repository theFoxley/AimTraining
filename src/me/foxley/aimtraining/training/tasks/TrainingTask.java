package me.foxley.aimtraining.training.tasks;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public abstract class TrainingTask {

    public static final byte EASY_DIFFICULTY = 10;
    public static final byte NORMAL_DIFFICULTY = 15;
    public static final byte HARD_DIFFICULTY = 20;
    public static final byte EXTREME_DIFFICULTY = 25;

    protected byte distance;
    protected byte targetCount;

    public TrainingTask(byte distance_, byte targetCount_) {
        this.distance = distance_;
        this.targetCount = targetCount_;
    }

    protected abstract Vector getNextTargetDirection(Location source);

    public Location getNextTargetLocation(Location source) {
        return source.clone().add(getNextTargetDirection(source).multiply(distance));
    }

    public byte getTargetCount() {
        return targetCount;
    }

}
