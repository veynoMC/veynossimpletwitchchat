package eu.veyno.veynosSimpleTwitchChat.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TwitchTabCompleter implements TabCompleter {
    @Override
    public  List<String> onTabComplete( CommandSender commandSender,  Command command,  String s,  String  [] args) {
        List<String> completions = new ArrayList<>();

        if(args.length==1){
            completions.add("enable");
            completions.add("disable");
            completions.add("reload");
        }

        return completions;
    }
}
