package main.service;

import main.model.Task;

import java.util.List;


public interface TaskService {

    Task getById(Integer id);

    List<Task> getAll();

    Task save (Task task);

    boolean deleteById(Integer id);

    void deleteAll();
}
