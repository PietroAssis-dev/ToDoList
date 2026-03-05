package com.toDoList.controller;

import com.toDoList.entity.ToDoEntity;
import com.toDoList.services.ToDoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDoController {
    private ToDoService toDoService;

    public ToDoController(ToDoService toDoService){
        this.toDoService = toDoService;
    }


    @PostMapping
    List<ToDoEntity> create(@RequestBody @Valid ToDoEntity toDoEntity){
        return toDoService.create(toDoEntity);
    }
    @GetMapping
    List<ToDoEntity> listagem(){
        return toDoService.listagem();
    }
    @PutMapping
    List<ToDoEntity> update(@RequestBody ToDoEntity toDoEntity){
        return toDoService.update(toDoEntity);
    }

    @DeleteMapping("{id}")
    List<ToDoEntity> delete(@PathVariable("id") Long id){
        return toDoService.delete(id);
    }

}
