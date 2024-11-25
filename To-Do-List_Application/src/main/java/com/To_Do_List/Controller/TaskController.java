package com.To_Do_List.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.To_Do_List.Model.Task;
import com.To_Do_List.Service.TaskService;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Display homepage with task list
    @GetMapping
    public String homepage(Model model) {
        List<Task> tasks = taskService.getAllTask();
        model.addAttribute("tasks", tasks);
        model.addAttribute("task", new Task());
        return "index";
    }

    // Display the add/update task page
    @GetMapping("/addingpage")
    public String addTaskPage(@RequestParam(name = "id", required = false) Integer taskId, Model model) {
        Task task = new Task();
        if (taskId != null) {
            task = taskService.getTaskById(taskId);
        }
        model.addAttribute("task", task);
        return "addtask";
    }

    // Add a new task or update an existing one (mapping for both)
    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task) {
        taskService.saveTask(task);
        return "redirect:/api/tasks";
    }

    // Navigate to the list page for tasks
    @GetMapping("/listpage")
    public String ListPage(Model model) {
        List<Task> tasks = taskService.getAllTask();

        List<Task> completedTasks = new LinkedList<>();
        List<Task> pendingTasks = new LinkedList<>();
        List<Task> inProgressTasks = new LinkedList<>();

        for (Task task : tasks) {
            if ("Completed".equalsIgnoreCase(task.getStatus())) {
                completedTasks.add(task);
            } else if ("Pending".equalsIgnoreCase(task.getStatus())) {
                pendingTasks.add(task);
            } else if ("In Progress".equalsIgnoreCase(task.getStatus())) {
                inProgressTasks.add(task);
            }
        }
        model.addAttribute("completedTasks", completedTasks);
        model.addAttribute("pendingTasks", pendingTasks);
        model.addAttribute("inProgressTasks", inProgressTasks);

        return "list";
    }

    // Delete a task
    @PostMapping("/delete")
    public String deleteTask(@RequestParam("taskId") Integer taskId) {
        taskService.delete(taskId);
        return "redirect:/api/tasks/listpage";
    }

    // View the update task page
    @GetMapping("/addingpage/{id}")
    public String showUpdatePage(@PathVariable("id") Integer id, Model model) {
        Task task = taskService.getTaskById(id);  // Fetch the task by ID
        model.addAttribute("task", task);  // Add the task object to the model
        return "addtask";  // Return the update page view
    }

    // Update a task
    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable("id") Integer id, @ModelAttribute Task task) {
        task.setId(id);  // Ensure the task ID is correctly set for update
        taskService.saveTask(task);  // Save the updated task
        return "redirect:/api/tasks";  // Redirect back to task list
    }    
}
