package com.example.shayan.securitylearning01.service;

import java.time.LocalDateTime;

// import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shayan.securitylearning01.repository.Todo;
import com.example.shayan.securitylearning01.repository.TodoRepository;
import com.example.shayan.securitylearning01.repository.User;

import jakarta.transaction.Transactional;

@Service
public class TodoService {
    
    private TodoRepository todoRepository;

    TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional
    public void createNewTodo(Todo todo,User user){
        try {
            todo.setUser(user);
            todo.setUpdatedAt(LocalDateTime.now());
            todoRepository.save(todo);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create new todo:\n" + e.getMessage());
        }
    }
}
