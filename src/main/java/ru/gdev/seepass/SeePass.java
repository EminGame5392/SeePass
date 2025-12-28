package ru.gdev.seepass;

import ru.gdev.seepass.listeners.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public class SeePass extends JavaPlugin {
    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.getCommand("texpass").setExecutor(new SeePassCommand(this));

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);

        getLogger().info("TexPass has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("TexPass has been disabled!");
    }

    public String getKickMessage() {
        return getConfig().getString("kick-message", "§cУ вас нету проходки на этот сервер!§b Купить её можно на сайте Texonia.su");
    }

    public void givePass(String playerName) {
        getConfig().set("players." + playerName, true);
        saveConfig();
    }

    public void takePass(String playerName) {
        getConfig().set("players." + playerName, null);
        saveConfig();
    }

    public boolean hasPass(String playerName) {
        return getConfig().getBoolean("players." + playerName, false);
    }
}