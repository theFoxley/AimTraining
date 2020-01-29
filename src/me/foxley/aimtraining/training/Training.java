package me.foxley.aimtraining.training;

import me.foxley.aimtraining.training.modes.Mode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Iterator;

public class Training {

    private TrainingManager trainingManager;
    private Player player;
    private Mode mode;

    private Location location;

    private ArrayList<TrainingBlock> blocks;
    private short totalHitCount;
    private short successHitCount;

    private long startTime;

    public Training(TrainingManager trainingManager_, Player player_, Mode mode_, Location location_) {
        this.trainingManager = trainingManager_;
        this.player = player_;
        this.mode = mode_;
        this.location = location_;
        this.totalHitCount = 0;
        this.successHitCount = 0;

        this.preparePlayer();

        blocks = new ArrayList<TrainingBlock>(2);
        blocks.add(new TrainingBlock(player, mode.getNextLocation(player.getEyeLocation())));

        startTime = System.currentTimeMillis();
    }

    public void onInteract() {
        Location source = player.getEyeLocation();
        Vector direction = source.getDirection();

        totalHitCount++;

        Iterator<TrainingBlock> iterator = blocks.iterator();

        while (iterator.hasNext()) {
            TrainingBlock trainingBlock = iterator.next();
            if (trainingBlock.isBlockHit(source, direction)) { // Si un block est touche
                trainingBlock.changeBlock(player, mode.getNextLocation(source));
                successHitCount++;
            }
        }

        TrainingBoard.updatePrecision(player, (100 * successHitCount / totalHitCount));

        if(successHitCount == 25) {
            finished();
        }
    }

    public void finished() {
        long time = System.currentTimeMillis() - startTime;
        player.sendMessage(ChatColor.BLUE + "---------- Overview ----------");
        player.sendMessage(ChatColor.BLUE + "Temps : " + ChatColor.GOLD + (int) (time/1000) + "s");
        player.sendMessage(ChatColor.BLUE + "Precision : " + ChatColor.GOLD + (int) (100 * successHitCount / totalHitCount) + "%");
        player.sendMessage(ChatColor.BLUE + "Kill : " + ChatColor.GOLD + successHitCount);


        trainingManager.unregisterTraining(player);
    }

    public void unregister() {
        for (TrainingBlock block : blocks) {
            block.destroyBlock(player);
        }
        blocks.clear();
        player = null;
        blocks = null;
    }

    private void preparePlayer() {

        for (Player p : Bukkit.getOnlinePlayers()) {
            player.hidePlayer(p);
        }

        player.teleport(location.clone().add(0.5, 0.5, 0.5));
        player.getInventory().clear();

        for (int k = 0; k < 9; k++) {
            player.getInventory().setItem(k, new ItemStack(Material.STONE_BUTTON));
        }

        TrainingBoard.setScoreboard(player);
    }
}
