package com.todolist.todolist.Repository;

import com.todolist.todolist.Entity.ToDoItem;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ToDoItemRepository extends CrudRepository<ToDoItem, UUID> {
}