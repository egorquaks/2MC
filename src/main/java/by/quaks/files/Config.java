package by.quaks.files;

import org.bukkit.configuration.file.FileConfiguration;

public interface Config {
    public static void setup() {   }
    public static FileConfiguration get(){return null;}
    public static void save(){  }
    public static void reload(){    }
}
