package Classes;

import java.time.LocalDate;

public class GraduateStudent extends Student {
    public LocalDate graduateDate;

    public GraduateStudent(String name, LocalDate graduateDate) {
        super(name, graduateDate.minusYears(5));
    }
}
