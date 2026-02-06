package model;
import exception.InvalidInputException;


public abstract class Shinobi extends BaseEntity implements Validatable {
    private int chakraLevel;

    public Shinobi(int id, String name, int chakraLevel) {
        super(id, name);
        this.chakraLevel = chakraLevel;
        public void validate() throws InvalidInputException {
            if (getName() == null || getName().trim().isEmpty()) {
                throw new InvalidInputException("Имя шиноби не может быть пустым!");
            }
            if (getChakraLevel() <= 0) {
                throw new InvalidInputException("Уровень чакры должен быть положительным!");
            }
            logValidationSuccess(); // Вызов default метода
        }
    }

    public int getChakraLevel() { return chakraLevel; }

    public abstract void performJutsu();
    public abstract String getNinjaWay();

    public void reportStatus() {
        System.out.println(getName() + " готов к миссии. Чакра: " + chakraLevel);
    }
}