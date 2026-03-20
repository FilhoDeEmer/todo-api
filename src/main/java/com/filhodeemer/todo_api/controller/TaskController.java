package com.filhodeemer.todo_api.controller;

import com.filhodeemer.todo_api.model.Task;
import com.filhodeemer.todo_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> listarTodas() {
        return taskRepository.findAll();
    }

    @PostMapping
    public Task criar(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task atualizar(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}