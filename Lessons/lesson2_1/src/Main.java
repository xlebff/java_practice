//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static class Person {
        private String lastName,
                firstName,
                middleName;
        private String gender;
        private int age;

        public Person(String lastName,
                      String firstName,
                      String middleName,
                      String gender,
                      int age) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.middleName = middleName;
            this.gender = gender;
            this.age = age;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public String getGender() {
            return gender;
        }

        public int getAge() {
            return age;
        }

        public void printInfo() {
            System.out.println(lastName + " "
                    + firstName + " "
                    + middleName + ", Пол: " + gender
                    + ", Возраст: " + age);
        }
    }

    static class SchoolStudent extends Person {
        private String nationality;
        private int height, weight;
        private String birthDate;
        private String phone;
        private String address;
        private String school;
        private int grade;

        public SchoolStudent(String lastName,
                             String firstName,
                             String middleName,
                             String gender,
                             int age,
                             String nationality,
                             int height,
                             int weight,
                             String birthDate,
                             String phone,
                             String address,
                             String school,
                             int grade) {
            super(lastName, firstName, middleName, gender, age);
            this.nationality = nationality;
            this.height = height;
            this.weight = weight;
            this.birthDate = birthDate;
            this.phone = phone;
            this.address = address;
            this.school = school;
            this.grade = grade;
        }

        @Override
        public void printInfo() {
            System.out.println(getLastName() + " "
                    + getFirstName() + " "
                    + getMiddleName()
                    + ", Пол: " + getGender()
                    + ", Возраст: " + getAge()
                    + ", Национальность: " + nationality
                    + ", Рост: " + height + " см"
                    + ", Вес: " + weight + " кг"
                    + ", Дата рождения: " + birthDate
                    + ", Телефон: " + phone
                    + ", Адрес: " + address
                    + ", Школа: " + school
                    + ", Класс: " + grade);
        }
    }

    public static void main(String[] args) {
        SchoolStudent[] students = {
                new SchoolStudent("Иванов", "Пётр", "Сергеевич",
                        "М", 15, "Русский", 170, 60,
                        "2009-05-15", "+7-999-111-22-33",
                        "123456, Россия, Москва, ул. Ленина, д.10, кв.5",
                        "Школа №5", 9),

                new SchoolStudent("Петрова", "Анна", "Игоревна",
                        "Ж", 14, "Русская", 160, 50,
                        "2010-03-20", "+7-999-222-33-44",
                        "123456, Россия, Москва, ул. Гагарина, д.15, кв.12",
                        "Школа №5", 8),

                new SchoolStudent("Сидоров", "Дмитрий", "Александрович",
                        "М", 16, "Русский", 180, 70,
                        "2008-11-01", "+7-999-333-44-55",
                        "123456, Россия, Москва, ул. Пушкина, д.20, кв.8",
                        "Школа №5", 10)
        };

        System.out.println("=== ВСЕ УЧЕНИКИ ===");
        for (SchoolStudent s : students) {
            s.printInfo();
        }

        System.out.println("\n=== ВЫБОРКА: мальчики 14-16 лет ===");
        for (SchoolStudent s : students) {
            if (s.getGender().equals("М") && s.getAge() >= 14 && s.getAge() <= 16) {
                s.printInfo();
            }
        }
    }
}