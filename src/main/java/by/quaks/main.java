package by.quaks;

import by.quaks.listeners.DiscordSrvListener;
import by.quaks.listeners.chat.VanillaChatDisabler;
import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {

    static main main; // Экземпляр Ntmcore
    public static main getInstance(){
        return main;
    } // Это нужно для того, чтобы можно было получить экземпляр(переменная main) из других классов и юзать приколы из JavaPlugin

    private void registerListeners(Listener[] a){ // Функция регистрации листенеров
        for (Listener elem : a) {
            getServer().getPluginManager().registerEvents(elem, this);
        }
    }

    private DiscordSrvListener discordsrvListener = new DiscordSrvListener(this); // Переменная типа DiscordSRVListener. Использование см. ниже

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("2MC успешно запущен"); // Перенести в конфиг языка
        DiscordSRV.api.subscribe(discordsrvListener); // Подсос к прослушиванию Discord
        registerListeners(new Listener[]{ // Регестрируем листенеры
                new VanillaChatDisabler()
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        DiscordSRV.api.unsubscribe(discordsrvListener);// Отсос от прослушивания Discord
    }
}
