package com.shen.todoapp.controllers;

import com.shen.todoapp.models.ToDo;
import com.shen.todoapp.repositories.ToDoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ToDoController {
    @Autowired
    ToDoRepository toDoRepository;
    //getAllTodos
    @GetMapping("/todos")
    public List<ToDo> getAllTodos(){
        Sort sortByCreatedAtDesc = Sort.by(Sort.Direction.DESC,"createdAt");
        return toDoRepository.findAll(sortByCreatedAtDesc);
    }
    //createToDo
    @PostMapping("/todos")
    public ToDo createToDo(@Valid @RequestBody ToDo todo){
        return toDoRepository.save(todo);
    }

    @GetMapping(value= "/todos/{id}")
    //getToDoById
    public ResponseEntity<ToDo> getToDoById(@PathVariable("id") String id){
        return toDoRepository.findById(id)
                .map(todo->ResponseEntity.ok().body(todo))
                .orElse(ResponseEntity.notFound().build());
    }
    //updateTodo
    @PutMapping("/todos/{id}")
    public ResponseEntity<ToDo> updateToDo(@PathVariable("id") String id,@Valid @RequestBody ToDo todo){
        return toDoRepository.findById(id)
                .map(todoData->{
                    todoData.setTitle(todo.getTitle());
                    todoData.setCompleted(todo.getCompleted());
                    todoData.setText(todo.getText());
                    ToDo updatedTodo = toDoRepository.save(todoData);
                    return ResponseEntity.ok().body(updatedTodo);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    //deleteTodo
    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteToDo(@PathVariable("id") String id){
        return toDoRepository.findById(id)
                .map(todo -> {
                    toDoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    //search

}
