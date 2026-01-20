package model;
public class WarriorNinja extends Shinobi {
    public WarriorNinja(int id, String name, int chakraLevel) {
        super(id, name, chakraLevel);
    }
    @Override
    public void performJutsu() { System.out.println(getName() + " использует Расенган!"); }
    @Override
    public String getNinjaWay() { return "Никогда не сдаваться!"; }
}