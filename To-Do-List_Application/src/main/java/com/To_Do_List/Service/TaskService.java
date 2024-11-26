package com.To_Do_List.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.To_Do_List.Model.Task;
import com.To_Do_List.Repository.TaskRepo;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    // Save Task (handles both create and update)
    public Task saveTask(Task task) {
        if (task.getId() != null) {
            Task existingTask = taskRepo.findById(task.getId()).orElse(null);
            if (existingTask != null) {
                task.setCreatedAt(existingTask.getCreatedAt());
            }
        }

        if (task.getCreatedAt() == null) {
            task.setCreatedAt(LocalDateTime.now());
        }

        task.setUpdatedAt(LocalDateTime.now());
        return taskRepo.save(task);
    }


    // Get All Tasks method
    public List<Task> getAllTask() {
        return taskRepo.findAll();
    }

    // Get Task by ID method
    public Task getTaskById(Integer id) {
        return taskRepo.findById(id).orElse(null);
    }
    
    // Delete Task method
    public void delete(Integer id) {
        taskRepo.deleteById(id);
    }
    
}
