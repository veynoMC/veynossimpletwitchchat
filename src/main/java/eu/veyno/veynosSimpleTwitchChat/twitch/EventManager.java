package eu.veyno.veynosSimpleTwitchChat.twitch;

import eu.veyno.veynosSimpleTwitchChat.VeynosSimpleTwitchChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EventManager implements Listener {

    @EventHandler
    public void onTwitchChat(TwitchChatEvent e){
        if(VeynosSimpleTwitchChat.enabled){
            if(e.isSubscriber()){
                String message =
                        ChatColor.RESET
                                + ""
                                + ChatColor.DARK_PURPLE
                                + ""
                                + ChatColor.BOLD
                                + "[Twitch] "
                                + ChatColor.LIGHT_PURPLE
                                + e.getSenderName()
                                + ChatColor.GRAY
                                + ": "
                                + ChatColor.DARK_RED
                                + e.getMessage();
                Bukkit.broadcastMessage(message);

            }
            else {
                String message =
                        ChatColor.RESET
                                + ""
                                + ChatColor.DARK_PURPLE
                                + ""
                                + ChatColor.BOLD
                                + "[Twitch] "
                                + ChatColor.LIGHT_PURPLE
                                + e.getSenderName()
                                + ChatColor.GRAY
                                + ": "
                                + ChatColor.AQUA
                                + e.getMessage();
                Bukkit.broadcastMessage(message);
            }
        }
    }
}
