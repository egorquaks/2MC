package by.quaks;

import by.quaks.files.Config;
import by.quaks.listeners.DiscordSrvListener;
import by.quaks.listeners.chat.VanillaChatDisabler;
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
    private void initConfigs(Object[] a){ // Метод регистрации файлов
        for (Object obj : a){
            try {
                Method method = obj.getClass().getMethod("setup");
                method.invoke(obj);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private DiscordSrvListener discordsrvListener = new DiscordSrvListener(this); // Переменная типа DiscordSRVListener. Использование см. ниже

    @Override
    public void onEnable() {
        if(!this.getDataFolder().exists()) {
            try {
                this.getDataFolder().mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        initConfigs(new Object[]{
                new Config()
        });
        registerListeners(new Listener[]{ // Регестрируем листенеры
                new VanillaChatDisabler()
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
