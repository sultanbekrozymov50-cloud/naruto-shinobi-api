package service;

import model.Shinobi;
import repository.ShinobiRepository;
import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import java.util.List;

public class ShinobiService {
    private final ShinobiRepository repository;

    public ShinobiService(ShinobiRepository repository) {
        this.repository = repository;
    }

    public void addShinobi(Shinobi shinobi) throws InvalidInputException {
        if (shinobi.getName() == null || shinobi.getName().trim().isEmpty()) {
            throw new InvalidInputException("Имя шиноби не может быть пустым!");
        }
        if (shinobi.getChakraLevel() <= 0) {
            throw new InvalidInputException("Уровень чакры должен быть положительным!");
        }
        repository.create(shinobi);
    }

    public List<Shinobi> getAll() {
        return repository.getAll();
    }

    public Shinobi getById(int id) throws ResourceNotFoundException {
        Shinobi shinobi = repository.getById(id);
        if (shinobi == null) {
            throw new ResourceNotFoundException("Шиноби с ID " + id + " не найден в базе данных.");
        }
        return shinobi;
    }

    public void deleteShinobi(int id) throws ResourceNotFoundException {
        if (repository.getById(id) == null) {
            throw new ResourceNotFoundException("Ошибка удаления: Шиноби с ID " + id + " не существует.");
        }
        repository.delete(id);
    }
}