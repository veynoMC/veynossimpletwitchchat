package eu.veyno.veynosSimpleTwitchChat.command;

import eu.veyno.veynosSimpleTwitchChat.VeynosSimpleTwitchChat;
import eu.veyno.veynosSimpleTwitchChat.twitch.TwitchChat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TwitchCommand implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender commandSender,  Command command,  String s,  String  [] args) {
        if(args[0].equalsIgnoreCase("enable")){
            VeynosSimpleTwitchChat.enabled = true;
            Component c = Component.text("Twitch chat enabled");
            c=c.color(TextColor.color(52, 168, 50));
            commandSender.sendMessage(c);
        }
        else if(args[0].equalsIgnoreCase("disable")){
            VeynosSimpleTwitchChat.enabled = false;
            Component c = Component.text("Twitch chat disabled");
            c=c.color(TextColor.color(52, 168, 50));
            commandSender.sendMessage(c);
        }
        else if(args[0].equalsIgnoreCase("reload")){
            TwitchChat.connect();
            Component c = Component.text("Twitch connection reloading...");
            c=c.color(TextColor.color(52, 168, 50));
            commandSender.sendMessage(c);
        }
        return true;
    }
}
