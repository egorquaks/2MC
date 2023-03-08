package by.quaks.chat.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class VanillaChatDisabler implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
//        if(isEmpty(unPrefix('!',event.getMessage()))){
//            event.setMessage("!☺");
//        }
        event.setCancelled(true);
    }
}
