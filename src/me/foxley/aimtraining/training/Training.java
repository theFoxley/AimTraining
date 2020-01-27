package me.foxley.aimtraining.training;

import me.foxley.aimtraining.training.generator.Generator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Iterator;

public class Training {

    private Player player;
    private Generator generator;

    private Location location;

    private ArrayList<TrainingBlock> blocks;

    public Training(Player player_, Generator generator_, Location location_) {
        this.player = player_;
        this.generator = generator_;
        this.location = location_;

        this.createTrainingBase();
        this.player.teleport(location);

        blocks = new ArrayList<TrainingBlock>(2);
    }

    public void onInteract() {
        Location source = player.getEyeLocation();
        Vector direction = source.getDirection();

        Iterator<TrainingBlock> iterator = blocks.iterator();

        while(iterator.hasNext()) {
            TrainingBlock trainingBlock = iterator.next();
            if(trainingBlock.isBlockHit(source, direction)) { // Si un block est touche
                trainingBlock.changeBlock(player.getWorld(), generator.getNextLocation(source));
            }
        }
    }

    public void finished() {
        for (TrainingBlock block : blocks) {
            block.destroyBlock(player.getWorld());
        }
        blocks.clear();

        player = null;
        this.destroyTrainingBase();
    }

    private void createTrainingBase() {
        this.setTypeTrainingBase(Material.GLASS);
    }

    private void destroyTrainingBase() {
        this.setTypeTrainingBase(Material.AIR);
    }

    private void setTypeTrainingBase(Material material) {
        int xBase = location.getBlockX();
        int yBase = location.getBlockY();
        int zBase = location.getBlockZ();

        World world = location.getWorld();

        world.getBlockAt(xBase,yBase,zBase).setType(material);
        world.getBlockAt(xBase+1,yBase+1,zBase).setType(material);
        world.getBlockAt(xBase-1,yBase+1,zBase).setType(material);
        world.getBlockAt(xBase,yBase+1,zBase+1).setType(material);
        world.getBlockAt(xBase,yBase+1,zBase-1).setType(material);
        world.getBlockAt(xBase,yBase+3,zBase).setType(material);
    }
}
