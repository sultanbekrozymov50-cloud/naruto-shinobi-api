import model.Shinobi;
import model.WarriorNinja;
import model.MedicNinja;
import model.Team;
import service.ShinobiService;
import repository.ShinobiRepository;
import exception.InvalidInputException;
import controller.ShinobiController;
import repository.ShinobiRepository;
import service.ShinobiService;
public class Main {
    public static void main(String[] args) {
        ShinobiRepository repository = new ShinobiRepository();
        ShinobiService service = new ShinobiService(repository);

        try {
            System.out.println("--- Инициализация Конохи ---");

            service.addShinobi(new WarriorNinja(0, "Sasuke Uchiha", 800));
            service.addShinobi(new MedicNinja(0, "Sakura Haruno", 400));

          //  System.out.println("\nСписок всех шиноби:");
           // for (Shinobi s : service.getAll()) {
            //    System.out.print("ID: " + s.getId() + " | " + s.getName() + " -> ");
            //    s.performJutsu();
            //}

            //System.out.println("\n--- Аналитика по базе ---");

            //repository.printTop3Strongest();

            //repository.printAverageChakra();

            //System.out.println("\nПроверка валидации:");
            //try {
              //  service.addShinobi(new WarriorNinja(0, "", -10));
            //} catch (InvalidInputException e) {
              //  System.out.println("Поймали ожидаемую ошибку: " + e.getMessage());
            //}
            System.out.println("\n--- HIgest chakra level ---");
            repository.Printthemostchakralevel();
            repository.printPowerful();
            try {
                service.addShinobi(new WarriorNinja(0, "", -10));
            } catch (InvalidInputException e) {
                System.out.println("found the error" + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            ShinobiRepository repository = new ShinobiRepository();
            ShinobiService service = new ShinobiService(repository);
            ShinobiController controller = new ShinobiController(service);

            // Запуск приложения
            controller.start();
        }
    }
}