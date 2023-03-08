package by.quaks.chat.listeners;

import by.quaks.chat.utils.ChatSender;
import by.quaks.files.MainConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

public class LocalChatListener implements Listener {
    char globalChatPrefix = Objects.requireNonNull(MainConfig.get().getString("GlobalChatPrefix")).charAt(0);
    char customChatPrefix = '@';
    public void perform(AsyncPlayerChatEvent event){
        onPlayerChat(event);
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        if (event.getMessage().charAt(0) == globalChatPrefix){return;}
        if (event.getMessage().charAt(0) == '@'){return;}
        ChatSender.sendLocalMessage(event);
    }
}
