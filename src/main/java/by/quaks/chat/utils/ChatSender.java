package by.quaks.chat.utils;

import by.quaks.files.ChatFormatting;
import by.quaks.files.ChatRooms;
import by.quaks.files.MainConfig;
import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.util.WebhookUtil;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ChatSender {
    public static void sendGlobalMessage(AsyncPlayerChatEvent event){
        //Minecraft
        Player p = event.getPlayer();
        String msg = event.getMessage();
        if (event.getMessage().substring(1).equals("")){return;}
        TextComponent textComponent = (MessageGenerator.genPlayerChatMessage(MessageGenerator.MessageType.Global,p, msg));
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            // Check if the player is within the specified radius
            player.sendMessage(textComponent);
        }
        //Discord
        LocalTime time = LocalTime.now();
        int hoursOffset = ChatFormatting.get().getInt("Time.HoursOffset");
        int minutesOffset = ChatFormatting.get().getInt("Time.MinutesOffset");
        LocalTime utcTime = time.plusHours(hoursOffset).plusMinutes(minutesOffset);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = utcTime.format(formatter);
        WebhookUtil.deliverMessage(DiscordSRV.getPlugin().getOptionalTextChannel("global"), event.getPlayer(), event.getMessage().substring(1).replaceAll("%time%",formattedTime));
    }
    public static void sendLocalMessage(AsyncPlayerChatEvent event){
        Player p = event.getPlayer();
        String msg = event.getMessage();
        double radius = MainConfig.get().getDouble("LocalChatRadius");
        TextComponent message = MessageGenerator.genPlayerChatMessage(MessageGenerator.MessageType.Local,p, msg);
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            // Check if the player is within the specified radius
            if (p.getLocation().distance(player.getLocation()) <= radius) {
                // Send the message to the player
                player.spigot().sendMessage(message);
            }
        }
    }
    public static void sendCustomRoomMessage(AsyncPlayerChatEvent event){
        String room = event.getMessage().substring(0, 2);
        event.setMessage(event.getMessage().substring(2));
        if (event.getMessage().equals("")){return;}
        List<String> members = ChatRooms.get().getStringList(room + ".Members");
        TextComponent message = MessageGenerator.genPlayerChatMessage(MessageGenerator.MessageType.Custom, event.getPlayer(), event.getMessage(), room);
        for (String playerName : members){
            Player player = Bukkit.getPlayer(playerName);
            if (player == null){continue;}
                player.sendMessage(message);
        }
    }
}
