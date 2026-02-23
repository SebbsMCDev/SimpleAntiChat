package me.magicsebs.simpleAntiChat.manager;

import me.magicsebs.simpleAntiChat.SimpleAntiChat;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class WarningManager {

    private final SimpleAntiChat plugin;
    private final HashMap<UUID, Integer> warnings = new HashMap<>();

    public WarningManager(SimpleAntiChat plugin) {
        this.plugin = plugin;
    }

    public int addWarning(Player player) {
        UUID uuid = player.getUniqueId();
        int count = warnings.getOrDefault(uuid, 0) + 1;
        warnings.put(uuid, count);
        return count;
    }

    public void clearWarnings(Player player) {
        warnings.remove(player.getUniqueId());
    }

    public int getWarnings(Player player) {
        return warnings.getOrDefault(player.getUniqueId(), 0);
    }
}