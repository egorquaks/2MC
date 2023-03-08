package by.quaks.chat.utils;

import by.quaks.files.ChatRooms;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PrefixHandler {
    public static char getFirstChar(String message){
        return message.charAt(0);
    }
    public static String GetCustomPrefix(String message){
        String prefix;
        if (message.startsWith("@") && message.length() > 1) {
            prefix = message.substring(0, 2);
        }else return null;
        return prefix;
    }
    //TextComponent message, Collection<? extends Player> res, Player sender TODO
    public static List<Player> ListDeliverMessageTo(String prefix){
        Server server = Bukkit.getServer();
//        Bukkit.getLogger().info((ChatRooms.get().getStringList(prefix+".Members")+""));
        List<Player> players = new ArrayList<>(server.getOnlinePlayers());
        List<String> playerNames = new ArrayList<>();
        for (Player player : players) {
            String playerName = player.getName();
            playerNames.add(playerName);
        }
        playerNames.retainAll((ChatRooms.get().getStringList(prefix+".Members")));
        List<Player> return_players = new ArrayList<>();
        for (String playerName : playerNames) {
            Player player = server.getPlayer(playerName);
            if (player != null) {
                return_players.add(player);
            }
        }
//        for (Player player : return_players) {
//            player.sendMessage("Hello!");
//        }
//        Bukkit.getLogger().info(return_players+"");
        return return_players;
    }
}
