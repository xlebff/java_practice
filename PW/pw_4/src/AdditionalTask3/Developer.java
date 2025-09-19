package AdditionalTask3;

public class Developer extends BaseEmployee {
    private double hourlyRate;
    private int hoursWorked;

    public Developer(String name, double hourlyRate, int hoursWorked) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void addHoursWorked(int hoursWorked) {
        this.hoursWorked += hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return this.hoursWorked * this.hourlyRate;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Salary: " + calculateSalary());
    }
}