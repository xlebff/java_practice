package File;

import States.FileType;

public class File implements IFile {
    private String name;
    private FileType type;
    private String content;

    public File(String name, FileType type, String content) {
        this.name = name;
        this.type = type;
        this.content = content;
    }

    @Override
    public void open(User user) {
        System.out.println("Файл " + name + " открыт пользователем " + user + ".");
        System.out.println(this);
    }

    public String getName() {
        return name;
    }

    public FileType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }
}
