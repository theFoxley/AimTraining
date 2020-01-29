package me.foxley.aimtraining.training;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TrainingBoard {

    public static void setScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("aimtraining", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.AQUA + "AimTraining");

        Team precision = scoreboard.registerNewTeam("precision");
        Team time = scoreboard.registerNewTeam("time");

        precision.addEntry(ChatColor.WHITE + "" + ChatColor.WHITE);
        time.addEntry(ChatColor.GRAY + "" + ChatColor.WHITE);

        precision.setPrefix(ChatColor.GOLD + "%");
        time.setPrefix(ChatColor.GOLD + "s");

        objective.getScore("").setScore(5);
        objective.getScore(ChatColor.BLUE + "> Precision").setScore(4);
        objective.getScore(ChatColor.WHITE + "" + ChatColor.WHITE).setScore(3);
        objective.getScore(" ").setScore(2);
        objective.getScore(ChatColor.BLUE + "> Temps").setScore(1);
        objective.getScore(ChatColor.GRAY + "" + ChatColor.WHITE).setScore(0);

        player.setScoreboard(scoreboard);
    }

    public static void updatePrecision(Player player, int precision) {
        Team precisionTeam = player.getScoreboard().getTeam("precision");
        precisionTeam.setPrefix(ChatColor.GOLD.toString() + precision + "%");
    }

    public static void updateTime(Player player, int time) {
        Team timeTeam = player.getScoreboard().getTeam("time");
        timeTeam.setPrefix(ChatColor.GOLD.toString() + time + "s");
    }

}
