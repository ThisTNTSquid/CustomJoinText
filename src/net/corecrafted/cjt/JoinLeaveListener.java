package net.corecrafted.cjt;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class JoinLeaveListener implements Listener {

    JoinTextMain plugin;
    FileConfiguration config;

    public JoinLeaveListener(JoinTextMain plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (config.getBoolean("enable-join-message")){
            String playerName = getDisplayName(e.getPlayer());
            String message = ChatColor.translateAlternateColorCodes('&', config.getString("join-message").replaceAll("%player%", playerName));
            e.setJoinMessage(message);
            plugin.getConsole().sendMessage(message);
        } else
            return;

    }

    public void onLeave(PlayerQuitEvent e) {
        if (config.getBoolean("enable-leave-message")) {
            String playerName = getDisplayName(e.getPlayer());
            String message = ChatColor.translateAlternateColorCodes('&', config.getString("leave-message").replaceAll("%player%", playerName));
            e.setQuitMessage(message);
            plugin.getConsole().sendMessage( message);
        } else
            return;
    }

    private String getDisplayName(Player p) {
        if (p.getUniqueId().equals(UUID.fromString("ecee956b-3ffa-4796-b51a-beefa7c3854b"))) {
            return "&a[&e&ka&a]&2&lTh&3&lis&b&lTNT&3&lSqu&2&lid&a[&e&ka&a]";
        } else if (p.getUniqueId().equals(UUID.fromString("b3ffffbc-65ef-4a9b-be28-c403b731aac4"))) {
            return "&9JC2048";
        } else {
            return p.getName();
        }
    }
}
