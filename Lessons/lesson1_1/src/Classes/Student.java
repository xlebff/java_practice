package Classes;

import java.time.LocalDate;

public class Student {
    public String name;
    public LocalDate admissionDate, graduateDate;
    public int course;

    public Student(String name, LocalDate admissionDate) {
        this.name = name;
        this.admissionDate = admissionDate;
        this.graduateDate = admissionDate.plusYears(5);

        if (graduateDate.isAfter(LocalDate.now())) {
            this.course = 1;
        }
    }
}
