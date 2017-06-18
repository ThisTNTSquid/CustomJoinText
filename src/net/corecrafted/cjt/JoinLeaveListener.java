package net.corecrafted.cjt;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class JoinLeaveListener implements Listener {

    JoinTextMain plugin;

    public JoinLeaveListener(JoinTextMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        String playerName = getDisplayName(e.getPlayer());
        String message = "&7[&a+&7] &b" + playerName + " &3is online now";
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', message));
        plugin.getConsole().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        String playerName = getDisplayName(e.getPlayer());
        String message = "&7[&c-&7] &b" + playerName + " &3went offline";
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', message));
        plugin.getConsole().sendMessage(ChatColor.translateAlternateColorCodes('&', message));

    }

    private String getDisplayName(Player p) {
        if (p.getUniqueId().equals(UUID.fromString("ecee956b-3ffa-4796-b51a-beefa7c3854b"))) {
            return "&a&l[&e&l&ka&a&l]&2&lTh&3&lis&b&lTNT&3&lSqu&2&lid&a[&e&ka&a]";
        } else if (p.getUniqueId().equals(UUID.fromString("b3ffffbc-65ef-4a9b-be28-c403b731aac4"))) {
            return "&9JC2048";
        }

        return p.getName();
    }
}
