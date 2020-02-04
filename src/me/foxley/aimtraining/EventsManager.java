package me.foxley.aimtraining;

import me.foxley.aimtraining.training.TrainingInterface;
import me.foxley.aimtraining.training.TrainingManager;
import me.foxley.aimtraining.training.tasks.TrainingTaskEnum;
import me.foxley.aimtraining.utils.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class EventsManager implements Listener {

    private AimTraining aimTraining;
    private TrainingManager trainingManager;
    private TrainingInterface trainingInterface;

    public EventsManager(AimTraining aimTraining_) {
        this.aimTraining = aimTraining_;
        this.trainingManager = new TrainingManager(aimTraining);
        this.trainingInterface = new TrainingInterface(trainingManager);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        aimTraining.setPlayerInSpawn(player);


        e.setJoinMessage(ChatUtil.getJoinMessage(player));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if (trainingManager.isTraining(player)) {
            trainingManager.unregisterTraining(player.getUniqueId());
        }

        e.setQuitMessage(ChatUtil.getQuitMessage(player));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action a = e.getAction();
        ItemStack item = e.getItem();

        if (!trainingManager.isTraining(player)) {
            if ((a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) && item != null) {
                if (item.getType() == TrainingInterface.material) { // On ouvre le menu
                    e.setCancelled(true);
                    trainingInterface.openMenu(player);
                }
            }
        } else {
            this.trainingManager.playerInteract(player);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null) { // On a cliqué à l'intérieur de l'inventaire
            if (event.getWhoClicked() instanceof Player) {
                Player player = (Player) event.getWhoClicked();
                if (event.getClickedInventory().getName().equalsIgnoreCase(trainingInterface.inventoryName)) { // On a cliqué sur le bon inventaire
                    event.setCancelled(true);
                    TrainingTaskEnum trainingTaskEnum = TrainingTaskEnum.getModeTypeAtPosition(event.getSlot());
                    if (trainingTaskEnum != null) {
                        player.sendMessage(ChatColor.BLUE + "Mode sélectionné : " + trainingTaskEnum.getName());
                        trainingManager.registerTraining(player, trainingTaskEnum.getMode());
                        player.closeInventory();
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

}
