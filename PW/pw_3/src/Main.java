import java.time.LocalDate;

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

    static class Student extends Person {
        private String nationality;
        private int height, weight;
        private LocalDate birthDate;
        private String phone;
        private String address;
        private String university;
        private int course;
        private String group;
        private float average;
        private String specialty;

        public Student(String lastName,
                       String firstName,
                       String middleName,
                       String gender,
                       int age,
                       String nationality,
                       int height,
                       int weight,
                       LocalDate birthDate,
                       String phone,
                       String address,
                       String university,
                       int course,
                       String group,
                       float average,
                       String specialty) {
            super(lastName, firstName, middleName, gender, age);
            this.nationality = nationality;
            this.height = height;
            this.weight = weight;
            this.birthDate = birthDate;
            this.phone = phone;
            this.address = address;
            this.university = university;
            this.course = course;
            this.group = group;
            this.average = average;
            this.specialty = specialty;
        }

        public float getAverage() {
            return this.average;
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
                    + ", ВУЗ: " + university
                    + ", Курс: " + course
                    + ", Группа: " + group
                    + ", Средний балл: " + average
                    + ", Специальность: " + specialty);
        }
    }

    public static void main(String[] args) {
        Student[] students = {
                new Student(
                    "Иванов",
                    "Иван",
                    "Иванович",
                    "Мужской",
                    20,
                    "Россия",
                    180,
                    75,
                    LocalDate.of(2003, 5, 15),
                    "+7-999-123-45-67",
                    "123456, Россия, Московская область, Ленинский район, г. Москва, ул. Ленина, д. 10, кв. 5",
                    "МГУ",
                    2,
                    "ИТ-21",
                    4.5f,
                    "Информационные технологии"
                ),

                new Student(
                    "Петрова",
                    "Мария",
                    "Сергеевна",
                    "Женский",
                    19,
                    "Россия",
                    165,
                    55,
                    LocalDate.of(2004, 8, 22),
                    "+7-999-987-65-43",
                    "654321, Россия, Ленинградская область, Выборгский район, г. Санкт-Петербург, ул. Пушкина, д. 25, кв. 12",
                    "МГУ",
                    1,
                    "ЭК-11",
                    4.7f,
                    "Экономика"
                ),

                new Student(
                    "Сидоров",
                    "Алексей",
                    "Владимирович",
                    "Мужской",
                    21,
                    "Россия",
                    178,
                    72,
                    LocalDate.of(2002, 11, 3),
                    "+7-999-456-78-90",
                    "987654, Россия, Новосибирская область, Центральный район, г. Новосибирск, пр. Мира, д. 8, кв. 34",
                    "МГУ",
                    3,
                    "ФИ-31",
                    4.3f,
                    "Физика"
                )
        };

        System.out.println("=== ВСЕ СТУДЕНТЫ ===");
        for (Student s : students) {
            s.printInfo();
        }

        System.out.println("\n=== ВЫБОРКА: студенты со средним баллом 4.5 и выше ===");
        for (Student s : students) {
            if (s.getAverage() >= 4.5) {
                s.printInfo();
            }
        }
    }
}