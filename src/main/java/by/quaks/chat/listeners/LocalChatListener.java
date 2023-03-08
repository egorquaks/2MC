package by.quaks.chat.listeners;

import by.quaks.chat.utils.ChatSender;
import by.quaks.chat.utils.MessageGenerator;
import by.quaks.files.MainConfig;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

public class LocalChatListener implements Listener {
    char globalChatPrefix = Objects.requireNonNull(MainConfig.get().getString("GlobalChatPrefix")).charAt(0);
    char customChatPrefix = '@';
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        if (event.getMessage().charAt(0) == globalChatPrefix){return;}
        ChatSender.sendLocalMessage(event);
    }
}
