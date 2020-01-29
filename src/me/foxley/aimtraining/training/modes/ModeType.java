package me.foxley.aimtraining.training.modes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum ModeType {

    EASY_FLICKING(ChatColor.GREEN + "Flicking facile", new FlickingMode((byte) 10), (byte) (9+2)),
    NORMAL_FLICKING(ChatColor.YELLOW + "Flicking normal", new FlickingMode((byte) 15), (byte) (9+3)),
    HARD_FLICKING(ChatColor.GOLD + "Flicking difficile", new FlickingMode((byte) 20), (byte) (9+5)),
    EXTREME_FLICKING(ChatColor.RED + "Flicking extrême", new FlickingMode((byte) 25), (byte) (9+6));

    private static final Material material = Material.RECORD_9;

    private final String name;
    private final Mode mode;
    private final byte position;

    ModeType(String name_, Mode mode_, byte position_) {
        this.name = name_;
        this.mode = mode_;
        this.position = position_;
    }

    public static ModeType getModeTypeAtPosition(int position) {
        int k = 0;
        ModeType[] modeTypes = ModeType.values();
        while(k< modeTypes.length && modeTypes[k].getPosition() != position) {
            k++;
        }

        if(k< modeTypes.length) {
            return modeTypes[k];
        } else {
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public Mode getMode() {
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
