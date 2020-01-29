package me.foxley.aimtraining.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class ChatUtil {

    public static String getJoinMessage(Player player) {
        return ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + player.getName() + ChatColor.GRAY + " a rejoint l'entraînement.";
    }

    public static String getQuitMessage(Player player) {
        return ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + player.getName() + ChatColor.GRAY + " a quitté l'entraînement.";
    }

}
