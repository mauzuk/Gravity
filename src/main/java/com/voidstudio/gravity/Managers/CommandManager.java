package com.voidstudio.gravity.Managers;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandManager implements CommandExecutor {
    private static final CommandManager instance = new CommandManager();
    public boolean toggled = false;

    public static CommandManager getInstance() {
        return instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("gravity")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("toggle")) {
                    if (!toggled) {
                        toggled = true;
                        sender.sendMessage(ChatColor.GREEN + "Gravity enabled.");
                    } else {
                        toggled = false;
                        sender.sendMessage(ChatColor.RED + "Gravity disabled.");
                    }
                }
            }
        }
        return false;
    }
}
