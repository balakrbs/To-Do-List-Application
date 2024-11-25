package com.To_Do_List.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        if (task.getId() == 0) {
            task.setCreatedAt(LocalDateTime.now());
        }
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepo.save(task); // Save the task to the database
    }
    public Task getTaskById(Integer id) {
    	return taskRepo.findById(id).orElse(null);
    }

    // Get All Tasks method
    public List<Task> getAllTask() {
        return taskRepo.findAll();
    }

    // Get Task by ID method
    public Optional<Task> getById(Integer id) {
        return taskRepo.findById(id);
    }
    
    // Delete Task method
    public void delete(Integer id) {
        taskRepo.deleteById(id);
    }
    
}
