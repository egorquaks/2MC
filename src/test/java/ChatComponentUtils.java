import net.md_5.bungee.api.chat.TextComponent;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatComponentUtils {

    // Replaces placeholders in a message with TextComponents
    public static TextComponent replacePlaceholders(String message, Map<String, TextComponent> placeholders) {
        TextComponent textComponent = new TextComponent();

        // Use a regular expression to match placeholders in the message
        Pattern pattern = Pattern.compile("%(.*?)%");
        Matcher matcher = pattern.matcher(message);
        int lastIndex = 0;

        // Iterate through the matches and replace them with TextComponents
        while (matcher.find()) {
            String placeholder = matcher.group(1);
            TextComponent replacement = placeholders.getOrDefault(placeholder, new TextComponent());
            if (matcher.start() > lastIndex) {
                // Add any non-matching text to the final TextComponent
                textComponent.addExtra(message.substring(lastIndex, matcher.start()));
            }
            // Add the replacement TextComponent to the final TextComponent
            textComponent.addExtra(replacement);
            lastIndex = matcher.end();
        }

        // Add any remaining non-matching text to the final TextComponent
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

        // Replace the placeholders in the message with TextComponents
        TextComponent textComponent = replacePlaceholders(message, placeholders);
        // Print the resulting TextComponent to the console
        System.out.println(textComponent.toLegacyText());
    }

}