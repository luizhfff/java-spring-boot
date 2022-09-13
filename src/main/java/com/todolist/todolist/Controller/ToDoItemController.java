package com.todolist.todolist.Controller;

import com.todolist.todolist.Entity.ToDoItem;
import com.todolist.todolist.Repository.ToDoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("todo")
@RestController
public class ToDoItemController {
    @Autowired
    private ToDoItemRepository toDoItemRepository;

    @GetMapping
    public Iterable<ToDoItem> getAll() {
        return toDoItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ToDoItem> getOne(@PathVariable UUID id) {
        return toDoItemRepository.findById(id);
    }

    @PostMapping
    public ToDoItem addItem(@RequestBody ToDoItem toDoItem) {
        toDoItem.setTimeCreated(new Date());
        toDoItem.setTimeModified(new Date());
        return toDoItemRepository.save(toDoItem);
    }

    @PutMapping("/{id}")
    public Optional<ToDoItem> editItem(@PathVariable UUID id, @RequestBody ToDoItem toDoItem) {
        return toDoItemRepository.findById(id).map(listItem -> {
            listItem.setText(toDoItem.getText());
            listItem.setTimeModified(new Date());
            return toDoItemRepository.save(listItem);
        });
    }

    @DeleteMapping("{id}")
    public void deleteItem(@PathVariable UUID id) {
        toDoItemRepository.deleteById(id);
    }
}
