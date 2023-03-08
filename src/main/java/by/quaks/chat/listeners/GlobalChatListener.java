package by.quaks.chat.listeners;

import by.quaks.files.MainConfig;
import by.quaks.chat.utils.ChatSender;
import by.quaks.chat.utils.PrefixHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

public class GlobalChatListener implements Listener {
    char prefix = Objects.requireNonNull(MainConfig.get().getString("GlobalChatPrefix")).charAt(0);
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        if((PrefixHandler.getFirstChar(event.getMessage())) == prefix){
            ChatSender.sendGlobalMessage(event);
        }
    }
}
