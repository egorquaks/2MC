package by.quaks;

import by.quaks.chat.listeners.LocalChatListener;
import by.quaks.commands.Reload;
import by.quaks.files.ChatRooms;
import by.quaks.files.Config;
import by.quaks.files.ChatFormatting;
import by.quaks.files.MainConfig;
import by.quaks.listeners.DiscordSrvListener;
import by.quaks.chat.listeners.GlobalChatListener;
import by.quaks.chat.listeners.VanillaChatDisabler;
import dev.jorel.commandapi.CommandAPI;
import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public final class main extends JavaPlugin {

    static main main; // Экземпляр Ntmcore
    public static main getInstance(){
        return main;
    } // Это нужно для того, чтобы можно было получить экземпляр(переменная main) из других классов и юзать приколы из JavaPlugin

    private void registerListeners(Listener[] a){ // Метод регистрации листенеров
        for (Listener elem : a) {
            getServer().getPluginManager().registerEvents(elem, this);
        }
    }
    private void initConfigs(Config[] a){ // Метод регистрации файлов
        for (Config obj : a){
            Class<?> clazz = obj.getClass();
            try {
                Method method = clazz.getDeclaredMethod("setup");
                method.invoke(null);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }



    private DiscordSrvListener discordsrvListener = new DiscordSrvListener(this); // Переменная типа DiscordSRVListener. Использование см. ниже

    @Override
    public void onEnable() {
        CommandAPI.onEnable(this);

        Reload.Register();

        if(!this.getDataFolder().exists()) { // Создание папки для хранения конфигурационных файлов
            try {
                this.getDataFolder().mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        initConfigs(new Config[]{
                new MainConfig(),
                new ChatRooms(),
                new ChatFormatting()
        });
        registerListeners(new Listener[]{ // Регестрируем листенеры чата
                new VanillaChatDisabler(),
                new GlobalChatListener(),
                new LocalChatListener()
        });
        DiscordSRV.api.subscribe(discordsrvListener); // Подсос к прослушиванию Discord
        getLogger().info("2MC успешно запущен"); // Перенести в конфиг языка
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        DiscordSRV.api.unsubscribe(discordsrvListener);// Отсос от прослушивания Discord
    }
}
