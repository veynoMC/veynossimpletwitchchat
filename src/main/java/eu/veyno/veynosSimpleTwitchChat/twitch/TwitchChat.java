package eu.veyno.veynosSimpleTwitchChat.twitch;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import eu.veyno.veynosSimpleTwitchChat.VeynosSimpleTwitchChat;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class TwitchChat {
    private static Plugin plugin;
    private static TwitchClient twitchClient;

    public static void setPlugin(Plugin plugin) {
        TwitchChat.plugin = plugin;
    }

    public static void unregister(){
        try {
            twitchClient.close();
        }
        catch (Exception e){
            Bukkit.getLogger().warning("Error while closing twitch connection " + e.getMessage());
        }
        }

    public static void registerChat(String oAuthkey){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
        twitchClient = TwitchClientBuilder.builder()
                .withEnableChat(true)
                .withChatAccount(new OAuth2Credential("twitch", oAuthkey))
                .build();
                    Bukkit.getScheduler().runTask(plugin, () -> {
                        twitchClient.getEventManager().onEvent(ChannelMessageEvent.class, event -> {
                            String senderName = event.getUser().getName();
                            String message = event.getMessage();
                            String channelName = event.getChannel().getName();
                            long timestamp = System.currentTimeMillis();
                            boolean isSubscriber = event.getPermissions().contains("SUBSCRIBER");
                            Bukkit.getScheduler().runTask(plugin, () -> {
                                TwitchChatEvent twitchEvent = new TwitchChatEvent(senderName, message, channelName, isSubscriber, timestamp);
                                Bukkit.getPluginManager().callEvent(twitchEvent);
                            });
                    });
            });
        });
    }


    public static void connect(){
        try {
            File file;

            FileConfiguration config;
            File pluginFolder = VeynosSimpleTwitchChat.instance.getDataFolder();
            File configFolder = new File(pluginFolder, "Twitch");
            if (!configFolder.exists()) {
                configFolder.mkdirs();
            }
            file = new File(configFolder, "auth.yml");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            config = YamlConfiguration.loadConfiguration(file);

            if (config.getString("twitchoauthtoken") == null) {
                config.set("twitchoauthtoken", "your_twitch_oauth_token");
                try {
                    config.save(file);
                } catch (Exception e) {

                }
            }
            String twitchOAuthToken = config.getString("twitchoauthtoken");
            TwitchChat.registerChat(twitchOAuthToken);
        }
        catch (Exception e){
            Bukkit.getLogger().info(e.getMessage());
        }
    }
}
