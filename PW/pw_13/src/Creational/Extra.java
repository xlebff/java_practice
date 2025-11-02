package Creational;

public class Extra extends NPC {
    private String role;
    private boolean isBusy;

    public Extra(String name, float health, String role) {
        super(NPCType.EXTRA, name, health);
        this.role = role;
        this.isBusy = false;
    }

    @Override
    public NPC clone() {
        return new Extra(name, health, role);
    }

    @Override
    public void getHit(float damage) {
        if (isAlive) {
            health -= damage;
            System.out.println("Массовка " + name + " ранена!");
            dying();
        }
    }

    public void performAction() {
        if (isAlive && !isBusy) {
            System.out.println(name + " (" + role + ") выполняет свое действие...");
            isBusy = true;
        }
    }

    public void rest() {
        if (isAlive) {
            isBusy = false;
            System.out.println(name + " отдыхает...");
        }
    }

    public void panic() {
        if (isAlive) {
            System.out.println(name + " впадает в панику и бежит!");
        }
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }
}