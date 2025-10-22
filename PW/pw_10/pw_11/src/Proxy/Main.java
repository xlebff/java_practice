package Proxy;

import Proxy.File.IFile;
import Proxy.File.ProxyFile;
import Proxy.File.User;
import Proxy.States.FileType;
import Proxy.States.UserRole;

public class Main {
    public static void main(String[] args) {
        User guest = new User("Гость", UserRole.GUEST);
        User user = new User("Иван", UserRole.USER);
        User admin = new User("Админ", UserRole.ADMIN);

        IFile publicFile = new ProxyFile(
                "readme.txt", FileType.PUBLIC, "Невероятный файл");

        IFile confidentialFile = new ProxyFile(
                "отчет.xlsx", FileType.PROTECTED, "Конфиденциальный файл");

        IFile secretFile = new ProxyFile(
                "пароли.txt", FileType.PRIVATE, "Секретный файл");

        System.out.println();
        publicFile.open(guest);
        System.out.println();
        secretFile.open(guest);

        System.out.println();
        System.out.println("---------");
        System.out.println();

        secretFile.open(user);
        System.out.println();
        confidentialFile.open(user);

        System.out.println();
        System.out.println("---------");
        System.out.println();

        secretFile.open(admin);
    }
}