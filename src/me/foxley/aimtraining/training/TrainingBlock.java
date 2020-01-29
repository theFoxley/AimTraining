package me.foxley.aimtraining.training;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;


public class TrainingBlock {

    private int x;
    private int y;
    private int z;

    public TrainingBlock(Player player, Location position) {
        this.x = position.getBlockX();
        this.y = position.getBlockY();
        this.z = position.getBlockZ();

        this.createBlock(player);
    }

    @SuppressWarnings("deprecation")
    private void createBlock(Player player) {
        player.sendBlockChange(new Location(player.getWorld(), x, y, z), Material.REDSTONE_BLOCK, (byte) 0);
    }

    public boolean isBlockHit(Location source, Vector direction) {
        return checkXCollision(source, direction, x) || checkXCollision(source, direction, x + 1)
                || checkYCollision(source, direction, y) || checkYCollision(source, direction, y + 1)
                || checkZCollision(source, direction, z) || checkZCollision(source, direction, z + 1);
    }

    public void changeBlock(Player player, Location newPosition) {
        this.destroyBlock(player);

        this.x = newPosition.getBlockX();
        this.y = newPosition.getBlockY();
        this.z = newPosition.getBlockZ();

        this.createBlock(player);
    }

    @SuppressWarnings("deprecation")
    public void destroyBlock(Player player) {
        player.sendBlockChange(new Location(player.getWorld(), x, y, z), Material.AIR, (byte) 0);
    }

    private boolean checkXCollision(Location source, Vector direction, int xSide) {
        double time = (xSide - source.getX()) / direction.getX();
        double zAxis = source.getZ() + time * direction.getZ() - z;
        double yAxis = source.getY() + time * direction.getY() - y;
        return (0 <= zAxis && zAxis <= 1 && 0 <= yAxis && yAxis <= 1);
    }

    private boolean checkYCollision(Location source, Vector direction, int ySide) {
        double time = (ySide - source.getY()) / direction.getY();
        double xAxis = source.getX() + time * direction.getX() - x;
        double zAxis = source.getZ() + time * direction.getZ() - z;
        return (0 <= xAxis && xAxis <= 1 && 0 <= zAxis && zAxis <= 1);
    }

    private boolean checkZCollision(Location source, Vector direction, int zSide) {
        double time = (zSide - source.getZ()) / direction.getZ();
        double xAxis = source.getX() + time * direction.getX() - x;
        double yAxis = source.getY() + time * direction.getY() - y;
        return (0 <= xAxis && xAxis <= 1 && 0 <= yAxis && yAxis <= 1);
    }

}
