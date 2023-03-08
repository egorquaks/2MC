package by.quaks.chat.utils;

import by.quaks.files.ChatFormatting;
import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.util.WebhookUtil;

import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ChatSender {
    public static void sendGlobalMessage(AsyncPlayerChatEvent event){
        //Minecraft
        Player p = event.getPlayer();
        String msg = event.getMessage();
        p.spigot().sendMessage(MessageGenerator.genPlayerChatMessage2(MessageGenerator.MessageType.Global,p, msg));
        //Discord
        LocalTime time = LocalTime.now();
        int hoursOffset = ChatFormatting.get().getInt("Time.HoursOffset");
        int minutesOffset = ChatFormatting.get().getInt("Time.MinutesOffset");
        LocalTime utcTime = time.plusHours(hoursOffset).plusMinutes(minutesOffset);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = utcTime.format(formatter);
        WebhookUtil.deliverMessage(DiscordSRV.getPlugin().getOptionalTextChannel("global"), event.getPlayer(), event.getMessage().substring(1).replaceAll("%time%",formattedTime));
    }
}
