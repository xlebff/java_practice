package AdditionalTask3;

public abstract class BaseEmployee implements Employee {
    private final String role = "Employee";
    private String name;

    public BaseEmployee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getRole() {
        return "Employee";
    }

    @Override
    public abstract double calculateSalary();

    public void printInfo() {
        System.out.println("Name: " + name + "\nRole: " + getRole());
    }
}
