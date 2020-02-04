package me.foxley.aimtraining.training;

import me.foxley.aimtraining.training.tasks.TrainingTaskEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TrainingInterface {

    public static Material material = Material.JUKEBOX;
    public static String inventoryName = "Choix de l'entrainement";

    private TrainingManager trainingManager;

    public TrainingInterface(TrainingManager trainingManager_) {
        this.trainingManager = trainingManager_;
    }

    public static ItemStack getItem() {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + inventoryName);
        item.setItemMeta(itemMeta);

        return item;
    }

    public void openMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9*5, inventoryName);

        TrainingTaskEnum[] generators = TrainingTaskEnum.values();

        for(TrainingTaskEnum generator : generators) {
            inventory.setItem(generator.getPosition(), generator.getItem());
        }

        player.openInventory(inventory);
    }

}
