import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static List<User> users = new ArrayList<>();
    private static List<Message> messages = new ArrayList<>();
    private static Map<String, Integer> tags = new HashMap<String, Integer>();

    public static void main(String[] args) {
        final Pattern MENTION_PATTERN = Pattern.compile("(?<!(\\S))@(\\w+)\\b");
        final Pattern TAG_PATTERN = Pattern.compile("(?<!(\\S))#\\w+\\b");

        String[] str = {
                "12:01 Anna: Привет @Ivan! Как дела? #meeting",
                "12:05 Ivan: @Anna все хорошо, спасибо! #work",
                "12:10 Peter: @Anna @Ivan встреча в 13:00 #meeting #urgent",
                "12:15 Anna: Отлично! test@email.com - это email, а не упоминание #reminder",
                "12:20 Ivan: Понял, спасибо за информацию @Peter #work"
        };

        tagsCounting(TAG_PATTERN, str);
        mentionCounting(MENTION_PATTERN, str);
        messagesAnalysis(str);

        for(User user : users) {
            System.out.println("Name: " + user.getName() +
                                "\nMentions: " + user.getMentionsCount() +
                                "\nMessages: " + user.getMessagesCount() + "\n");
        }

        tags.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .forEach(entry ->
                        System.out.println(entry.getKey() + ": " + entry.getValue() + " упоминаний"));
    }

    private static void tagsCounting(Pattern pattern, String[] export) {
        for(String str : export) {
            Matcher matcher = pattern.matcher(str);

            while(matcher.find()) {
                String tag = matcher.group();
                tags.putIfAbsent(tag, 0);
                tags.replace(tag, tags.getOrDefault(tag, 0) + 1);
            }
        }
    }

    private static void mentionCounting(Pattern pattern, String[] export) {
        for(String str : export) {
            Matcher matcher = pattern.matcher(str);

            while(matcher.find()) {
                String name = matcher.group().replace("@", "");

                User user = findUser(name);
                user.incMentionsCount();
            }
        }
    }

    private static void messagesAnalysis(String[] str) {
        for(String s : str) {
            Message message = new Message(s);
            messages.add(message);

            String sender = message.getSender();

            if (sender != null && !sender.trim().isEmpty()) {
                User user = findUser(sender);
                user.incMessagesCount();
                user.addMessage(message);
            }
        }
    }

    private static User findUser(String name) {
        if (name == null) {
            return null;
        }

        for(User user : users) {
            if(user.getName().equals(name)) return user;
        }
        User user = new User(name);
        users.add(user);
        return user;
    }
}