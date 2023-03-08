package by.quaks.chat.listeners;

import by.quaks.chat.utils.ChatSender;
import by.quaks.chat.utils.CustomRoomsUtils;
import by.quaks.chat.utils.MessageGenerator;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class CustomRoomListener implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        if (event.getMessage().charAt(0) != '@'){return;}
        if (CustomRoomsUtils.isAnyMember(event.getPlayer().getName())){
            if(CustomRoomsUtils.isMemberOf(event.getPlayer().getName(),event.getMessage().substring(0,2))){
                ChatSender.sendCustomRoomMessage(event);
            } else {
                ChatSender.sendLocalMessage(event);
            }
        } else {

            ChatSender.sendLocalMessage(event);
        }
    }
}
