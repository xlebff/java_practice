import Observer.Student;
import Subject.Teacher;

public class Main {
    public static void main(String[] args) {
        Teacher teacher = new Teacher("Клёвый учитель");

        Student student1 = new Student("Студент 1", "student1");
        Student student2 = new Student("Студент 2", "student2");
        Student student3 = new Student("Студент 3", "student3");

        System.out.println();

        teacher.addObserver(student1);
        teacher.addObserver(student2);
        teacher.addObserver(student3);

        System.out.println();

        teacher.addObserver(student1);

        System.out.println("\n---------\n");

        teacher.createTask(
                "Написать эссе",
                "Напишите эссе на тему 'Паттерны проектирования' объёмом 1000 слов.",
                "2024-06-10"
        );

        System.out.println("\n" + teacher.getTasks());

        System.out.println("\n---------\n");

        teacher.removeObserver(student2);

        System.out.println("\n---------\n");

        teacher.createTask(
                "Лабораторная работа",
                "Реализовать паттерн Observer на Java.",
                "2024-06-17"
        );

        System.out.println("\n" + teacher.getTasks());

        System.out.println("\n---------\n");

        teacher.removeObserver(student2);
    }
}