package utils;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionUtils {
    public static void inspectClass(Object obj) {
        Class<?> clazz = obj.getClass();
        System.out.println("\n=== Reflection Analysis (RTTI) ===");
        System.out.println("Class: " + clazz.getSimpleName());
        System.out.println("Methods:");

        // Лямбда для вывода методов
        Arrays.stream(clazz.getDeclaredMethods())
                .forEach(m -> System.out.println(" -> " + m.getName()));
        System.out.println("================================\n");
    }
}