package me.magicsebs.simpleAntiChat.listeners;

import me.magicsebs.simpleAntiChat.SimpleAntiChat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final SimpleAntiChat plugin;

    public ChatListener(SimpleAntiChat plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("simpleantichat.bypass")) {
            return;
        }

        String message = event.getMessage();

        if (plugin.getFilterManager().containsBlockedWord(message)) {

            event.setCancelled(true);

            player.sendMessage(
                    plugin.getFilterManager().color(
                            plugin.getConfig().getString("messages.prefix")
                                    + plugin.getConfig().getString("messages.blocked-message")
                    )
            );

            if (plugin.getConfig().getBoolean("warnings.enabled")) {
                int warnings = plugin.getWarningManager().addWarning(player);

                player.sendMessage(
                        plugin.getFilterManager().color(
                                plugin.getConfig().getString("messages.warning-message")
                                        .replace("%current%", String.valueOf(warnings))
                                        .replace("%max%", String.valueOf(
                                                plugin.getConfig().getInt("warnings.max-warnings")
                                        ))
                        )
                );

                if (warnings >= plugin.getConfig().getInt("warnings.max-warnings")) {
                    String command = plugin.getConfig()
                            .getString("warnings.punishment-command")
                            .replace("%player%", player.getName());

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                    plugin.getWarningManager().clearWarnings(player);
                }
            }

            if (plugin.getConfig().getBoolean("staff-notifications.enabled")) {
                String alert = plugin.getConfig().getString("messages.staff-alert")
                        .replace("%player%", player.getName())
                        .replace("%message%", message);

                Bukkit.getOnlinePlayers().stream()
                        .filter(p -> p.hasPermission("simpleantichat.notify"))
                        .forEach(p -> p.sendMessage(
                                plugin.getFilterManager().color(
                                        plugin.getConfig().getString("messages.prefix") + alert
                                )
                        ));
            }
        }
    }
}