package net.corecrafted.cjt;

import com.avaje.ebeaninternal.server.idgen.UuidIdGenerator;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.Contexts;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.User;
import me.lucko.luckperms.api.caching.MetaData;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class JoinLeaveListener implements Listener {

    JoinTextMain plugin;
    FileConfiguration config;
    String joinMsgPermissions;
    String leaveMsgPermisisons;

    public JoinLeaveListener(JoinTextMain plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
        joinMsgPermissions = config.getString("join-message-permissions");
        leaveMsgPermisisons = config.getString("leave-message-permissions");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        if (config.getBoolean("enable-join-message")) {
            String playerName = getDisplayName(e.getPlayer());
            if (joinMsgPermissions != null) {
                if (e.getPlayer().hasPermission(joinMsgPermissions)) {
                    sendJoinMsg(e, playerName);
                } else
                    e.setJoinMessage(null);

            } else {
                sendJoinMsg(e, playerName);
            }

        } else
            e.setJoinMessage(null);

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeave(PlayerQuitEvent e) {
        if (config.getBoolean("enable-leave-message")) {
            String playerName = getDisplayName(e.getPlayer());
            if (leaveMsgPermisisons != null) {
                if (e.getPlayer().hasPermission(leaveMsgPermisisons)) {
                    sendLeaveMsg(e, playerName);
                } else
                    e.setQuitMessage(null);
            } else {
                sendLeaveMsg(e, playerName);
            }

        } else
            e.setQuitMessage(null);
    }

    private String getDisplayName(Player p) {
        if (p.getUniqueId().equals(UUID.fromString("ecee956b-3ffa-4796-b51a-beefa7c3854b"))) {
            return "&b&lT&a&lh&b&li&a&ls&b&lT&a&lN&b&lT&a&lS&b&lq&a&lu&b&li&a&ld";
        } else {
            return p.getName();
        }
    }

    private void sendJoinMsg(PlayerJoinEvent e, String playerName) {
        String message = ChatColor.translateAlternateColorCodes('&', config.getString("join-message").replaceAll("%player%", getPrefixSuffix(e.getPlayer().getUniqueId()).get(0) + " " + getPrefixSuffix(e.getPlayer().getUniqueId()).get(1) + playerName));
        e.setJoinMessage(message);
        plugin.getConsole().sendMessage(message);
    }

    private void sendLeaveMsg(PlayerQuitEvent e, String playerName) {
        String message = ChatColor.translateAlternateColorCodes('&', config.getString("leave-message").replaceAll("%player%", getPrefixSuffix(e.getPlayer().getUniqueId()).get(0) + " " + getPrefixSuffix(e.getPlayer().getUniqueId()).get(1) + playerName));
        e.setQuitMessage(message);
        plugin.getConsole().sendMessage(message);
    }

    private ArrayList<String> getPrefixSuffix(UUID uuid) {
        ArrayList<String> arr = new ArrayList<>();
        if (config.getBoolean("include-prefix")) {
            Optional<LuckPermsApi> provider = LuckPerms.getApiSafe();
            if (provider.isPresent()) {
                final LuckPermsApi api = provider.get();
                User user = api.getUser(uuid);
                Contexts contexts = api.getContextForUser(user).orElse(null);
                if (contexts == null) {
                    arr.add("");
                    arr.add("");
                    return arr;
                }
                MetaData metaData = user.getCachedData().getMetaData(contexts);
                arr.add(0, metaData.getPrefix());
                arr.add(1, metaData.getSuffix());
                return arr;

            } else {
                arr.add(0, "");
                arr.add(1, "");
                return arr;
            }
        } else {
            arr.add(0, "");
            arr.add(1, "");
        }

        return arr;
    }
}
