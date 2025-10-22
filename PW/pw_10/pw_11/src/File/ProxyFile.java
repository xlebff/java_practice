package File;

import States.FileType;

public class ProxyFile implements IFile {
    private final File file;

    public ProxyFile(String name, FileType type, String content) {
        file = new File(name, type, content);
    }

    @Override
    public void open(User user) {
        if (!user.hasAccess(file.getType())) {
            System.out.println("У пользователя " + user
                    + " недостаточно прав для открытия файлов типа " + file.getType() + ".");
        } else file.open(user);
    }

    public String getName() {
        return file.getName();
    }

    public FileType getType() {
        return file.getType();
    }

    @Override
    public String toString() {
        return file.getContent();
    }
}
