package ru.gdev.seepass;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SeePassCommand implements CommandExecutor {
    private final SeePass plugin;

    public SeePassCommand(SeePass plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("§cИспользование: /texpass give [ник] или /texpass take [ник|all]");
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "give":
                if (args.length == 2) {
                    String playerName = args[1];
                    plugin.givePass(playerName);
                    sender.sendMessage("§aПроходка выдана игроку " + playerName);
                } else {
                    sender.sendMessage("§cИспользование: /texpass give [ник]");
                }
                break;


            case "help":
                sender.sendMessage("§fПомощь по плагину §bTexPass:");
                sender.sendMessage("§b/texpass give [ник] §f- Выдать проходку игроку.");
                sender.sendMessage("§b/texpass take [ник] §f- Забрать проходку у игрока.");
                sender.sendMessage("§b/texpass help §f- Помощь по плагину.");
                break;

            case "take":
                if (args.length == 2) {
                    if (args[1].equalsIgnoreCase("all")) {
                        plugin.getConfig().set("players", null);
                        plugin.saveConfig();
                        sender.sendMessage("§aПроходка забрана у всех игроков.");
                    } else {
                        String playerName = args[1];
                        plugin.takePass(playerName);
                        sender.sendMessage("§aПроходка забрана у игрока " + playerName);
                    }
                } else {
                    sender.sendMessage("§cИспользование: /texpass take [ник|all]");
                }
                break;

            default:
                sender.sendMessage("§cИспользование: /texpass give [ник] или /texpass take [ник|all]");
                break;
        }

        return true;
    }
}