package Observer;

import Subject.Task;
import Subject.Teacher;

public interface Observer {
    void notify(Teacher teacher, Task task);
}
