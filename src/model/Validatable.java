package model;

import exception.InvalidInputException;

public interface Validatable {
    void validate() throws InvalidInputException;

    default void logValidationSuccess() {
        System.out.println(">>> [LOG]: Валидация объекта прошла успешно.");
    }

    static boolean isCreateable(int id) {
        return id == 0; 
    }
}