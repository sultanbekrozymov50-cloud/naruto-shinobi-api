package utils;

import model.Shinobi;
import java.util.List;
import java.util.Comparator;

public class SortingUtils {
    // Использование Generics и Lambda выражений
    public static void sortByChakraDesc(List<Shinobi> ninjas) {
        System.out.println("--- Сортировка списка (Lambda) ---");
        ninjas.sort((n1, n2) -> Integer.compare(n2.getChakraLevel(), n1.getChakraLevel()));
    }
}