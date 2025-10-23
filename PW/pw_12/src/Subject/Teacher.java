package Subject;

import Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private final String name;
    private List<Observer> students;
    private List<Task> tasks;

    public Teacher(String name) {
        this.name = name;
        students = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Observer> getStudents() {
        return students;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addObserver(Observer observer) {
        if (students.contains(observer)) System.out.println("Ученик " + observer + " уже подписан на уведомления.");
        else {
            students.add(observer);
            System.out.println("Ученик " + observer + " только что подписался на уведомления.");
        }
    }

    public void removeObserver(Observer observer) {
        if (!students.contains(observer)) System.out.println("Ученик " + observer + " не подписан на уведомления.");
        else {
            students.remove(observer);
            System.out.println("Ученик " + observer + " только что отписался от уведомлений.");
        }
    }

    public void createTask(String title, String description, String deadline) {
        Task task = new Task(title, description, deadline);

        if (tasks.contains(task)) {
            System.out.println("Задание уже существует");
            return;
        }

        tasks.add(task);
        for (Observer student : students) student.notify(this, task);
    }
}
