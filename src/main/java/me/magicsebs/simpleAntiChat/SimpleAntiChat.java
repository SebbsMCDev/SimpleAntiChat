package me.magicsebs.simpleAntiChat;

import me.magicsebs.simpleAntiChat.commands.AntiChatCommand;
import me.magicsebs.simpleAntiChat.listeners.ChatListener;
import me.magicsebs.simpleAntiChat.manager.FilterManager;
import me.magicsebs.simpleAntiChat.manager.WarningManager;
import me.magicsebs.simpleAntiChat.util.MessageUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleAntiChat extends JavaPlugin {

    private static SimpleAntiChat instance;

    private FilterManager filterManager;
    private WarningManager warningManager;
    private MessageUtil messageUtil;

    @Override
    public void onEnable() {
        instance = this;

        // Create default config if it doesn't exist
        saveDefaultConfig();

        // Initialize utilities and managers
        this.messageUtil = new MessageUtil(this);
        this.filterManager = new FilterManager(this);
        this.warningManager = new WarningManager(this);

        // Register events
        getServer().getPluginManager().registerEvents(
                new ChatListener(this),
                this
        );

        // Register command safely
        if (getCommand("simpleantichat") != null) {
            getCommand("simpleantichat")
                    .setExecutor(new AntiChatCommand(this));
        } else {
            getLogger().severe("Command 'simpleantichat' not found in plugin.yml!");
        }

        getLogger().info("SimpleAntiChat has been enabled successfully.");
    }

    @Override
    public void onDisable() {
        getLogger().info("SimpleAntiChat has been disabled.");
    }

    // =============================
    // Getters
    // =============================

    public static SimpleAntiChat getInstance() {
        return instance;
    }

    public FilterManager getFilterManager() {
        return filterManager;
    }

    public WarningManager getWarningManager() {
        return warningManager;
    }

    public MessageUtil getMessageUtil() {
        return messageUtil;
    }
}