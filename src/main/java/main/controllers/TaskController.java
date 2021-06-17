package main.controllers;

import main.model.Task;
import main.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks/")
    public int addTask(Task task) {

        Task newTask = taskService.save(task);
        return newTask.getId();
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity deleteTask(@PathVariable int id) {

        Task task = taskService.getById(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskService.deleteById(id);
        return new ResponseEntity("Задача удалена id:" + id, HttpStatus.OK);
    }

    @PutMapping ("/tasks/{id}")
    public ResponseEntity updateTask(@PathVariable int id) {

        Task task = taskService.getById(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskService.save(task);
        return new ResponseEntity("Задача обновлена id:" + id, HttpStatus.OK);
    }

    @GetMapping("/tasks/")
    public List<Task> getTasks() {
        return taskService.getAll();
    }

    @DeleteMapping("/tasks/")
    public ResponseEntity deleteAllTasks() {
        taskService.deleteAll();
        return new ResponseEntity("Весь список задач удалён", HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity getTask(@PathVariable int id) {
        Task task = taskService.getById(id);
        if(task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }
}
