package Creational;

public class Villager extends NPC {
    private int age;
    private String profession;
    private String dialogue;

    public Villager(String name, int age, float health, String profession) {
        super(NPCType.VILLAGER, name, health);
        this.age = age;
        this.profession = profession;
        this.dialogue = "Привет, я " + name + ", местный " + profession;
    }

    public Villager(String name, int age, float health, String profession, String dialogue) {
        this(name, age, health, profession);
        this.dialogue = dialogue;
    }

    @Override
    public NPC clone() {
        return new Villager(name, age, health, profession, dialogue);
    }

    public void talk() {
        if (isTalkable()) {
            System.out.println(name + ": " + dialogue);
        } else {
            System.out.println(name + " не может говорить...");
        }
    }

    @Override
    public void getHit(float damage) {
        if (isAlive) {
            health -= damage;
            System.out.println("О нет! " + name + " ранен!");
            dying();
        }
    }

    public void offerQuest() {
        if (isTalkable()) {
            System.out.println(name + " предлагает вам задание...");
        }
    }

    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDialogue() {
        return dialogue;
    }
}