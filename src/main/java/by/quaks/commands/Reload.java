package by.quaks.commands;

import by.quaks.files.ChatFormatting;
import by.quaks.files.ChatRooms;
import by.quaks.files.MainConfig;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import org.bukkit.Bukkit;

public class Reload {
    public static void Register(){
        new CommandAPICommand("2MCreload")
                .withPermission(CommandPermission.OP)               // Required permissions
                .executes((sender, args) -> {
                    ChatFormatting.reload();
                    ChatRooms.reload();
                    MainConfig.reload();
                })
                .register();
    }
}
