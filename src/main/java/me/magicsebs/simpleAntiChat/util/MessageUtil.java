package me.magicsebs.simpleAntiChat.util;

import me.magicsebs.simpleAntiChat.SimpleAntiChat;
import org.bukkit.ChatColor;

public class MessageUtil {

    private final SimpleAntiChat plugin;

    public MessageUtil(SimpleAntiChat plugin) {
        this.plugin = plugin;
    }

    public String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public String getPrefix() {
        return color(plugin.getConfig().getString("messages.prefix"));
    }

    public String getMessage(String path) {
        String message = plugin.getConfig().getString("messages." + path);
        return color(getPrefix() + message);
    }

    public String format(String path) {
        String message = plugin.getConfig().getString("messages." + path);
        return color(message);
    }
}