package by.quaks.chat.utils;

import net.md_5.bungee.api.chat.TextComponent;

public class Message {
    private TextComponent full_message;

    private String prefix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public TextComponent getFull_message() {
        return full_message;
    }

    public void setFull_message(TextComponent full_message) {
        this.full_message = full_message;
    }

}
