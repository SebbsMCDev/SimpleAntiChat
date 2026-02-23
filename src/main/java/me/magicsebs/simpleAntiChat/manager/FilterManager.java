package me.magicsebs.simpleAntiChat.manager;

import me.magicsebs.simpleAntiChat.SimpleAntiChat;
import org.bukkit.ChatColor;

import java.util.List;

public class FilterManager {

    private final SimpleAntiChat plugin;
    private List<String> blockedWords;

    public FilterManager(SimpleAntiChat plugin) {
        this.plugin = plugin;
        loadWords();
    }

    public void loadWords() {
        blockedWords = plugin.getConfig().getStringList("blocked-words");
    }

    public boolean containsBlockedWord(String message) {

        String lower = message.toLowerCase();

        for (String word : blockedWords) {
            if (lower.contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}