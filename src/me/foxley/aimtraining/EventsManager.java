package me.foxley.aimtraining;

import me.foxley.aimtraining.training.TrainingManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class EventsManager implements Listener {

    private AimTraining aimTraining;
    private TrainingManager trainingManager;

    public EventsManager(AimTraining aimTraining_) {
        this.aimTraining = aimTraining_;
        this.trainingManager = new TrainingManager();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        trainingManager.addPlayer(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        trainingManager.removePlayer(player);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack item = e.getItem();

        if(aimTraining.isInSpawn(p)) {
            if((a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) && item != null) {
                if(item.getType() == Material.WOOL) {

                }
            }
        } else {
            this.trainingManager.playerInteract(p);
        }
    }

}
