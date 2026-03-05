package com.toDoList.services;

import com.toDoList.entity.ToDoEntity;
import com.toDoList.repository.ToDoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    private ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;

    }


    public List<ToDoEntity> create (ToDoEntity toDoEntity){
        toDoRepository.save(toDoEntity);
        return listagem();


    }

    public List<ToDoEntity> listagem (){
        Sort sort = Sort.by("prioridade").descending().and(Sort.by("nome").ascending());
        return toDoRepository.findAll(sort);


    }
    public List<ToDoEntity> update (ToDoEntity toDoEntity){
        toDoRepository.save(toDoEntity);
        return listagem();

    }
    public List<ToDoEntity> delete (Long id){
        toDoRepository.deleteById(id);
        return listagem();
    }
}
