package ru.gdev.seepass.listeners;

import ru.gdev.seepass.SeePass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.gdev.seepass.SeePass;

public class PlayerJoinListener implements Listener {
    private final SeePass plugin;

    public PlayerJoinListener(SeePass plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        if (!plugin.hasPass(playerName)) {
            player.kickPlayer(plugin.getKickMessage());
        }
    }
}