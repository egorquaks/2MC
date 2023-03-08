package by.quaks.chat.utils;

import by.quaks.files.ChatRooms;
import by.quaks.files.MainConfig;
import by.quaks.main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.function.Supplier;

public class CustomRoomsUtils {
    public static boolean isAnyMember(String playerName){
        FileConfiguration file = ChatRooms.get();
        for (String room : file.getKeys(false)) {
            //Bukkit.getLogger().warning(room);
            List<String> members = file.getStringList(room + ".Members");
            //Bukkit.getLogger().warning(members.toString());;
            if (members.contains(playerName)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isMemberOf(String playerName, String room){
        FileConfiguration file = ChatRooms.get();
        List<String> members = file.getStringList(room + ".Members");
        return members.contains(playerName);
    }
}
