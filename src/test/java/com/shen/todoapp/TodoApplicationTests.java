package com.shen.todoapp;

import com.shen.todoapp.controllers.ToDoController;
import com.shen.todoapp.models.ToDo;
import com.shen.todoapp.repositories.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TodoApplicationTests {

    @Autowired
    ToDoController toDoController;
    @Autowired
    ToDoRepository toDoRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetAll(){
        toDoController.getAllTodos().forEach(System.out::println);
    }

    ToDo testTodo = new ToDo("test");
    @Test
    void create(){
        System.out.println(toDoController.createToDo(testTodo));
    }

}
