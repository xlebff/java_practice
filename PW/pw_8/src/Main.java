import Classes.Gender;
import Classes.Post;
import Classes.PersonalChat;
import Classes.User;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /* System.out.println("Enter your name: ");
        String username = scanner.nextLine();

        System.out.println("Enter your email: ");
        String email = scanner.nextLine();

        System.out.println("Enter your date of birth (YYYY-MM-DD): ");
        String dateOfBirthStr = scanner.nextLine();
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);

        System.out.println("Enter your gender (male / female): ");
        String genderStr = scanner.nextLine().toUpperCase();
        Gender gender = Gender.valueOf(genderStr);

        User me = new User(username, email, dateOfBirth, gender); */

        Examples examples = new Examples();

        User me = examples.Anna;

        // 1. Демонстрация добавления в публичный чат
        System.out.println("1. ПУБЛИЧНЫЕ ЧАТЫ");
        System.out.println("Нахожусь ли я в чате '" + examples.Books + "'? " + examples.Books.isUserInChat(me));
        examples.Books.addMember(me);
        System.out.println("А теперь нахожусь ли я в чате '" + examples.Books + "'? " + examples.Books.isUserInChat(me));

        // Добавляем еще пользователей в чат
        examples.Books.addMember(examples.Alexy);
        examples.Books.addMember(examples.Dmitry);

        System.out.println("\nУчастники чата '" + examples.Books + "': " + examples.Books.getMembers());

        // 2. Демонстрация обмена сообщениями
        System.out.println("\n2. ОБМЕН СООБЩЕНИЯМИ");
        me.sendMessage(examples.Books, "Привет всем! Какие книги сейчас читаете?");
        examples.Elena.sendMessage(examples.Books, "Привет! Я сейчас читаю 'Мастер и Маргарита'");
        examples.Alexy.sendMessage(examples.Books, "Я предпочитаю техническую литературу");

        System.out.println("Сообщения в чате '" + examples.Books + "':");
        examples.Books.getMessages().forEach(msg ->
                System.out.println("  " + msg.getSender() + ": " + msg.getContent()));

        // 3. Демонстрация личных чатов
        System.out.println("\n3. ЛИЧНЫЕ ЧАТЫ");
        PersonalChat annaAlexyChat = new PersonalChat(me, examples.Alexy);
        me.sendMessage(annaAlexyChat, "Привет, Алексей! Как дела?");
        examples.Alexy.sendMessage(annaAlexyChat, "Привет, Анна! Все отлично, спасибо!");

        System.out.println("Сообщения в личном чате:");
        annaAlexyChat.getMessages(me).forEach(msg ->
                System.out.println("  " + msg.getSender() + ": " + msg.getContent()));

        // 4. Демонстрация системы постов
        System.out.println("\n4. СИСТЕМА ПОСТОВ");
        me.createPost("Мой первый пост", "Это содержимое моего первого поста в социальной сети!");
        me.createPost("Любимые книги", "Делюсь списком моих любимых книг...");

        Post myPost = me.getPostByTitle("Мой первый пост");
        System.out.println("Создан пост: " + myPost.getTitle());
        System.out.println("Автор: " + myPost.getAuthor());
        System.out.println("Содержимое: " + myPost.getContent());

        // 5. Демонстрация системы лайков
        System.out.println("\n5. СИСТЕМА ЛАЙКОВ");
        myPost.like(examples.Maria);
        myPost.like(examples.Dmitry);
        myPost.like(examples.Elena);

        System.out.println("Лайков у поста: " + myPost.getLikes());
        System.out.println("Пользователи, лайкнувшие пост: " + myPost.getLiked());

        // Демонстрация дизлайков
        myPost.unlike(examples.Alexy);
        System.out.println("Дизлайков у поста: " + myPost.getDislikes());

        // Удаление оценки
        myPost.removeRate(examples.Maria);
        System.out.println("Лайков после удаления: " + myPost.getLikes());

        // 6. Демонстрация комментариев
        System.out.println("\n6. СИСТЕМА КОММЕНТАРИЕВ");
        examples.Maria.writeComment(myPost, "Отличный пост, Анна!");
        examples.Dmitry.writeComment(myPost, "Интересно, жду продолжения!");
        examples.Elena.writeComment(myPost, "Согласна с предыдущими комментаторами!");

        System.out.println("Комментарии к посту '" + myPost.getTitle() + "':");
        myPost.getComments().forEach(comment ->
                System.out.println("  " + comment.getAuthor() + ": " + comment.getContent()));

        // 7. Демонстрация просмотров
        System.out.println("\n7. ПРОСМОТРЫ ПОСТОВ");
        myPost.watch(examples.Maria);
        myPost.watch(examples.Dmitry);
        System.out.println("Количество просмотров: " + myPost.getWatches());

        // 8. Демонстрация друзей
        System.out.println("\n8. СИСТЕМА ДРУЗЕЙ");
        me.addFriend(examples.Maria);
        me.addFriend(examples.Elena);
        me.addFriend(examples.Dmitry);

        System.out.println("Друзья " + me.getUsername() + ": " + me.getFriends());

        me.removeFriend(examples.Dmitry);
        System.out.println("Друзья после удаления: " + me.getFriends());

        // 9. Демонстрация админских функций
        System.out.println("\n9. АДМИНСКИЕ ФУНКЦИИ");
        System.out.println("Админ чата '" + examples.Books + "': " + examples.Books.getAdmin());
        examples.Books.setTitle(examples.Elena, "Книжный клуб 'Читаем вместе'");
        examples.Books.setInfo(examples.Elena, "Новое описание: Обсуждаем классику и современную литературу");

        System.out.println("Название чата: " + examples.Books.getTitle());
        System.out.println("Информация о чате: " + examples.Books.getInfo());

        // 10. Демонстрация редактирования
        System.out.println("\n10. РЕДАКТИРОВАНИЕ");
        myPost.edit(me, "Обновленный пост", "Это обновленное содержимое моего поста!");
        System.out.println("Заголовок после редактирования: " + myPost.getTitle());
        System.out.println("Содержимое после редактирования: " + myPost.getContent());

        // Попытка редактирования не автором
        myPost.edit(examples.Maria, "Взломанный пост", "Этот пост был взломан!");

        scanner.close();
    }
}