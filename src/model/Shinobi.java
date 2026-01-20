package model;

public abstract class Shinobi extends BaseEntity {
    private int chakraLevel;

    public Shinobi(int id, String name, int chakraLevel) {
        super(id, name);
        this.chakraLevel = chakraLevel;
    }

    public int getChakraLevel() { return chakraLevel; }

    public abstract void performJutsu();
    public abstract String getNinjaWay();

    public void reportStatus() {
        System.out.println(getName() + " готов к миссии. Чакра: " + chakraLevel);
    }
}