package me.foxley.aimtraining.training;

import me.foxley.aimtraining.training.tasks.TrainingTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Training {

    private static final byte TRAINING_DURATION = 60;

    private TrainingManager trainingManager;
    private Player player;
    private TrainingTask mode;

    private TrainingBlock[] blocks;
    private short totalHitCount = 0;
    private short successHitCount = 0;

    private byte time = 4;
    private boolean hasBegun = false;

    public Training(TrainingManager trainingManager_, Player player_, TrainingTask mode_, Location location) {
        this.trainingManager = trainingManager_;
        this.player = player_;
        this.mode = mode_;

        blocks = new TrainingBlock[mode.getTargetCount()];
        this.preparePlayer(location);
    }

    public void onInteract() {
        if (hasBegun) {
            Location source = player.getEyeLocation();
            Vector direction = source.getDirection();

            totalHitCount++;

            int k = 0;
            while (k < blocks.length && !blocks[k].isBlockHit(source, direction)) {
                k++;
            }

            if (k < blocks.length) {
                blocks[k].changeBlock(player, mode.getNextTargetLocation(source));
                successHitCount++;
            }

            TrainingBoard.updatePrecision(player, (100 * successHitCount / totalHitCount));
        }
    }

    public void finished() {
        if (successHitCount == 0) {
            successHitCount = 1;
        }
        if (totalHitCount == 0) {
            totalHitCount = 1;
        }
        player.sendMessage(ChatColor.BLUE + "---------- Résultat ----------");
        player.sendMessage(ChatColor.BLUE + "Temps : " + ChatColor.GOLD + TRAINING_DURATION + "s");
        player.sendMessage(ChatColor.BLUE + "Precision : " + ChatColor.GOLD + (int) (100 * successHitCount / totalHitCount) + "%");
        player.sendMessage(ChatColor.BLUE + "Cibles atteintes : " + ChatColor.GOLD + successHitCount);
        player.sendMessage(ChatColor.BLUE + "Temps de visée : " + ChatColor.GOLD + (1000 * TRAINING_DURATION) / successHitCount + "ms");

        trainingManager.setPlayerInSpawn(player);
    }

    public void unregister() {
        for (TrainingBlock block : blocks) {
            block.destroyBlock(player);
        }

        player = null;
        blocks = null;
    }

    private void preparePlayer(Location location) {

        for (Player p : Bukkit.getOnlinePlayers()) {
            player.hidePlayer(p);
        }

        player.teleport(location);
        player.getInventory().clear();

        for (int k = 0; k < 9; k++) {
            player.getInventory().setItem(k, new ItemStack(Material.STONE_BUTTON));
        }

        TrainingBoard.setScoreboard(player);
    }

    @SuppressWarnings("deprecation")
    public boolean onSecondClock() {
        boolean finished = false;
        time--;
        TrainingBoard.updateTime(player, time);

        if (!hasBegun) {
            player.sendTitle(ChatColor.GOLD + Integer.toString(time), "");
            if (time == 0) {
                player.sendTitle("", "");
                time = TRAINING_DURATION;
                hasBegun = true;
                for (int k = 0; k < blocks.length; k++) {
                    blocks[k] = new TrainingBlock(player, mode.getNextTargetLocation(player.getEyeLocation()));
                }
            }
        } else {
            if (time == 0) {
                finished();
                finished = true;
            }
        }

        return finished;
    }
}
