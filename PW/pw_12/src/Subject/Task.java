package Subject;

public class Task {
    private final String title;
    private final String description;
    private final String deadline;

    public Task(String title, String description, String deadline) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "Задание \"" + title + "\"\n" +
                description + "\n" +
                "Выполнить до: " + deadline;
    }
}
