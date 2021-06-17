package main;

import main.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {
    private static AtomicInteger currentId = new AtomicInteger(0);
    private static HashMap<Integer,Task> tasks = new HashMap<>();

    public static List<Task> getAllTasks()
    {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.addAll(tasks.values());
        return taskList;
    }

    public static int addTask(Task task)
    {
        int id = currentId.incrementAndGet();
        task.setId(id);
        tasks.put(id,task);
        return id;
    }

    public static Task getTask (int taskId)
    {
        if (tasks.containsKey(taskId))
        {
            return tasks.get(taskId);
        }
        return null;
    }

    public static boolean putTask(Task task)
    {
        if(tasks.containsKey(task.getId()))
        {
            tasks.put(task.getId(),task);
            return true;
        }
        return false;
    }

    public static void deleteTask(int taskId)
    {
        if (tasks.containsKey(taskId))
        {
            tasks.remove(taskId);
        }
    }

    public static void deleteAllTask()
    {
        tasks.clear();
    }
}