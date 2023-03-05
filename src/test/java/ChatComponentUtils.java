import net.md_5.bungee.api.chat.TextComponent;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatComponentUtils {

    public static TextComponent replacePlaceholders(String message, Map<String, TextComponent> placeholders) {
        TextComponent textComponent = new TextComponent();

        Pattern pattern = Pattern.compile("%(.*?)%");
        Matcher matcher = pattern.matcher(message);
        int lastIndex = 0;

        while (matcher.find()) {
            String placeholder = matcher.group(1);
            TextComponent replacement = placeholders.getOrDefault(placeholder, new TextComponent());
            if (matcher.start() > lastIndex) {
                textComponent.addExtra(message.substring(lastIndex, matcher.start()));
            }
            textComponent.addExtra(replacement);
            lastIndex = matcher.end();
        }

        if (lastIndex < message.length()) {
            textComponent.addExtra(message.substring(lastIndex));
        }

        return textComponent;
    }

    public static void main(String[] args) {
        String message = "Hello %playerName%! Your %chat-type% message is: %message%";

        Map<String, TextComponent> placeholders = new HashMap<>();
        placeholders.put("playerName", new TextComponent("John"));
        placeholders.put("chat-type", new TextComponent("global"));
        placeholders.put("message", new TextComponent("Hello world!"));

        TextComponent textComponent = replacePlaceholders(message, placeholders);
        System.out.println(textComponent.toLegacyText());
    }

}
