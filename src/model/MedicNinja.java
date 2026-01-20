package model;
public class MedicNinja extends Shinobi {
    public MedicNinja(int id, String name, int chakraLevel) {
        super(id, name, chakraLevel);
    }
    @Override
    public void performJutsu() { System.out.println(getName() + " использует Технику Мистической Ладони (лечение)."); }
    @Override
    public String getNinjaWay() { return "Жизнь товарища важнее всего."; }
}