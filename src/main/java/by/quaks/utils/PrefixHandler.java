package by.quaks.utils;

import by.quaks.files.ChatRooms;
import by.quaks.files.Config;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.awt.print.Paper;
import java.util.ArrayList;
import java.util.List;

public class PrefixHandler {
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
