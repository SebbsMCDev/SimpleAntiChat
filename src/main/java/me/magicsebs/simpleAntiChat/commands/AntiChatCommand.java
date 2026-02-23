package me.magicsebs.simpleAntiChat.commands;

import me.magicsebs.simpleAntiChat.SimpleAntiChat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AntiChatCommand implements CommandExecutor {

    private final SimpleAntiChat plugin;

    public AntiChatCommand(SimpleAntiChat plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command,
                             String label, String[] args) {

        if (!sender.hasPermission("simpleantichat.admin")) {
            sender.sendMessage(plugin.getFilterManager().color(
                    plugin.getConfig().getString("messages.no-permission")));
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            plugin.getFilterManager().loadWords();
            sender.sendMessage(plugin.getFilterManager().color(
                    plugin.getConfig().getString("messages.reload")));
            return true;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("warnings")) {
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage(plugin.getFilterManager().color(
                        plugin.getConfig().getString("messages.player-not-found")));
                return true;
            }

            int warnings = plugin.getWarningManager().getWarnings(target);
            sender.sendMessage("§e" + target.getName() + " has " + warnings + " warnings.");
            return true;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("clear")) {
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage(plugin.getFilterManager().color(
                        plugin.getConfig().getString("messages.player-not-found")));
                return true;
            }

            plugin.getWarningManager().clearWarnings(target);
            sender.sendMessage(plugin.getFilterManager().color(
                    plugin.getConfig().getString("messages.warnings-cleared")
                            .replace("%player%", target.getName())));
            return true;
        }

        sender.sendMessage("§cUsage: /" + label + " reload | warnings <player> | clear <player>");
        return true;
    }
}