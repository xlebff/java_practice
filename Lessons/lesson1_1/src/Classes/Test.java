package Classes;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        Student st0 = new Student("Maria", LocalDate.of(2025, 9, 1));
        Student st1 = new GraduateStudent("Danil", LocalDate.of(2018, 9, 1));

        System.out.printf(st1.admissionDate.toString());
    }
}