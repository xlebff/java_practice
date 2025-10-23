package Observer;

import Subject.Task;
import Subject.Teacher;

public class Student implements Observer {
    private final String name, userName;

    public Student(String name, String userName) {
        this.name = name;
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public void notify(Teacher teacher, Task task) {
        System.out.println(this + " был уведомлён учителем " + teacher +
                " о новом задании \"" + task.getTitle() + "\"");
    }

    @Override
    public String toString() {
        return name + " (" + userName + ")";
    }
}
