public class Person implements Restable, Workable {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }

    public void work() {
        System.out.printf("%s работает...\n", this.name);
    }

    public void rest() {
        System.out.printf("%s отдыхает...\n", this.name);
    }
}
