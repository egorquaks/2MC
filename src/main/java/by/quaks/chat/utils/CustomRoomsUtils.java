package by.quaks.chat.utils;

import by.quaks.files.MainConfig;

import java.util.List;

public class CustomRoomsUtils {
    public static boolean isMember(String playerName){
        for (String room : MainConfig.get().getKeys(false)) {
            List<String> members = MainConfig.get().getStringList(room + ".Members");
            if (members.contains(playerName)) {
                return true;
            }
        }
        return false;
    }
}
