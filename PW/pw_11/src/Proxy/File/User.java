package Proxy.File;

import Proxy.States.FileType;
import Proxy.States.UserRole;

public class User {
    private final String name;
    private final UserRole role;

    public User(String name, UserRole role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public UserRole getRole() {
        return role;
    }

    public boolean hasAccess(FileType type) {
        return switch (type) {
            case FileType.PUBLIC -> true;
            case FileType.PROTECTED -> this.role != UserRole.GUEST;
            case FileType.PRIVATE -> this.role == UserRole.ADMIN;
            default -> false;
        };
    }

    @Override
    public String toString() {
        return (name + " (" + role + ")");
    }
}
