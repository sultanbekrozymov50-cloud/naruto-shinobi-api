package controller;

import model.Shinobi;
import model.WarriorNinja;
import model.MedicNinja;
import service.ShinobiService;
import utils.ReflectionUtils;
import utils.SortingUtils;
import exception.InvalidInputException;

import java.util.List;
import java.util.Scanner;

public class ShinobiController {
    private final ShinobiService service;
    private final Scanner scanner;

    public ShinobiController(ShinobiService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Добро пожаловать в систему управления Конохой!");
        while (true) {
            System.out.println("\n1. Добавить Шиноби");
            System.out.println("2. Показать всех (с сортировкой)");
            System.out.println("3. Найти по ID");
            System.out.println("4. Reflection Test (Анализ класса)");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1 -> createShinobi();
                    case 2 -> showAll();
                    case 3 -> findById();
                    case 4 -> testReflection();
                    case 0 -> { return; }
                    default -> System.out.println("Неверный ввод.");
                }
            } catch (Exception e) {
                System.out.println("ОШИБКА: " + e.getMessage());
            }
        }
    }

    private void createShinobi() throws InvalidInputException {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Уровень чакры: ");
        int chakra = scanner.nextInt();
        System.out.print("Тип (1-Воин, 2-Медик): ");
        int type = scanner.nextInt();

        Shinobi ninja;
        if (type == 1) ninja = new WarriorNinja(0, name, chakra);
        else ninja = new MedicNinja(0, name, chakra);

        ninja.validate();
        service.addShinobi(ninja);
        System.out.println("Шиноби успешно добавлен!");
    }

    private void showAll() {
        List<Shinobi> list = service.getAll();
        SortingUtils.sortByChakraDesc(list);

        for (Shinobi s : list) {
            System.out.println(s.getId() + ": " + s.getName() + " [" + s.getChakraLevel() + "]");
        }
    }

    private void findById() throws Exception {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        Shinobi s = service.getById(id);
        System.out.println("Найден: " + s.getName());
        s.performJutsu();
    }

    private void testReflection() {
        Shinobi dummy = new WarriorNinja(0, "Test", 100);
        ReflectionUtils.inspectClass(dummy);
    }
}