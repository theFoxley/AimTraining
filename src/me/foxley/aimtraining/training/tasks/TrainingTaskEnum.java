package me.foxley.aimtraining.training.tasks;

import me.foxley.aimtraining.training.Training;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum TrainingTaskEnum {

    EASY_REFLEXSHOT(ChatColor.GREEN + "Reflexshot facile", new Reflexshot(TrainingTask.EASY_DIFFICULTY), (byte) (9+2)),
    NORMAL_REFLEXSHOT(ChatColor.YELLOW + "Reflexshot normal", new Reflexshot(TrainingTask.NORMAL_DIFFICULTY), (byte) (9+3)),
    HARD_REFLEXSHOT(ChatColor.GOLD + "Reflexshot difficile", new Reflexshot(TrainingTask.HARD_DIFFICULTY), (byte) (9+5)),
    EXTREME_REFLEXSHOT(ChatColor.RED + "Reflexshot extrême", new Reflexshot(TrainingTask.EXTREME_DIFFICULTY), (byte) (9+6)),

    EASY_MICROSHOT(ChatColor.GREEN + "Microshot facile", new Microshot(TrainingTask.EASY_DIFFICULTY), (byte) (2*9+2)),
    NORMAL_MICROSHOT(ChatColor.YELLOW + "Microshot normal", new Microshot(TrainingTask.NORMAL_DIFFICULTY), (byte) (2*9+3)),
    HARD_MICROSHOT(ChatColor.GOLD + "Microshot difficile", new Microshot(TrainingTask.HARD_DIFFICULTY), (byte) (2*9+5)),
    EXTREME_MICROSHOT(ChatColor.RED + "Microshot extrême", new Microshot(TrainingTask.EXTREME_DIFFICULTY), (byte) (2*9+6)),

    EASY_GRIDSHOT(ChatColor.GREEN + "Gridshot facile", new Gridshot(TrainingTask.EASY_DIFFICULTY), (byte) (3*9+2)),
    NORMAL_GRIDSHOT(ChatColor.YELLOW + "Gridshot normal", new Gridshot(TrainingTask.NORMAL_DIFFICULTY), (byte) (3*9+3)),
    HARD_GRIDSHOT(ChatColor.GOLD + "Gridshot difficile", new Gridshot(TrainingTask.HARD_DIFFICULTY), (byte) (3*9+5)),
    EXTREME_GRIDSHOT(ChatColor.RED + "Gridshot extrême", new Gridshot(TrainingTask.EXTREME_DIFFICULTY), (byte) (3*9+6));

    private static final Material material = Material.RECORD_9;

    private final String name;
    private final TrainingTask mode;
    private final byte position;

    TrainingTaskEnum(String name_, TrainingTask mode_, byte position_) {
        this.name = name_;
        this.mode = mode_;
        this.position = position_;
    }

    public static TrainingTaskEnum getModeTypeAtPosition(int position) {
        int k = 0;
        TrainingTaskEnum[] trainingTaskEnums = TrainingTaskEnum.values();
        while(k< trainingTaskEnums.length && trainingTaskEnums[k].getPosition() != position) {
            k++;
        }

        if(k< trainingTaskEnums.length) {
            return trainingTaskEnums[k];
        } else {
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public TrainingTask getMode() {
        return mode;
    }

    public byte getPosition() {
        return position;
    }

    public ItemStack getItem() {
        ItemStack itemStack = new ItemStack(material);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
