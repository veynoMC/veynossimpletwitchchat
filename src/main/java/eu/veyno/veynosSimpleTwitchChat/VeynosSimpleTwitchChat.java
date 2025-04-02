package eu.veyno.veynosSimpleTwitchChat;

import eu.veyno.veynosSimpleTwitchChat.command.TwitchCommand;
import eu.veyno.veynosSimpleTwitchChat.command.TwitchTabCompleter;
import eu.veyno.veynosSimpleTwitchChat.twitch.TwitchChat;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class VeynosSimpleTwitchChat extends JavaPlugin {

    public static boolean enabled = true;
    public static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("twitch").setExecutor(new TwitchCommand());
        getCommand("twitch").setTabCompleter(new TwitchTabCompleter());
        TwitchChat.setPlugin(this);
        TwitchChat.connect();
    }

    @Override
    public void onDisable() {
        TwitchChat.unregister();
    }
}
