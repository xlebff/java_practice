import Classes.Gender;
import Classes.PublicChat;
import Classes.User;

import java.time.LocalDate;

public class Examples {
    public User Anna = new User("Anna_Ivanova", "anna.ivanova@email.com",
                       LocalDate.of(1995, 3, 15), Gender.FEMALE);

    public User Alexy = new User("Alexey_Petrov", "alexey.p@company.com",
                       LocalDate.of(1990, 8, 22), Gender.MALE);

    public User Maria = new User("Maria_Smirnova", "maria.smirnova@mail.ru",
                       LocalDate.of(1988, 12, 5), Gender.FEMALE);

    public User Dmitry = new User("Dmitry_Kozlov", "d.kozlov@gmail.com",
                       LocalDate.of(1992, 7, 30), Gender.MALE);

    public User Elena = new User("Elena_Vasileva", "elena_vasileva@yahoo.com",
                       LocalDate.of(1998, 4, 18), Gender.FEMALE);

    public PublicChat JavaDev = new PublicChat(Anna, "Java Developers",
            "Чат для обсуждения Java, Spring, Hibernate и других технологий");

    public PublicChat Travel = new PublicChat(Alexy, "Путешествия по миру",
            "Делимся впечатлениями, советами и фотографиями из путешествий");

    public PublicChat Food = new PublicChat(Maria, "Вкусно и просто",
            "Рецепты, кулинарные советы и обсуждение готовки");

    public PublicChat Gym = new PublicChat(Dmitry, "Фитнес клуб",
            "Тренировки, питание, мотивация и здоровый образ жизни");

    public PublicChat Books = new PublicChat(Elena, "Книжный клуб",
            "Обсуждение книг, рекомендации и литературные новинки");

}
