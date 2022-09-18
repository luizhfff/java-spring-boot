package com.todolist.todolist.Controller;

import com.todolist.todolist.Entity.Item;
import com.todolist.todolist.Entity.List;
import com.todolist.todolist.Service.ItemService;
import com.todolist.todolist.Service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/to-do-list")
public class ListController {
    @Autowired
    private ListService listService;
    @Autowired
    private ItemService itemService;

    @GetMapping
    public Iterable<List> getAllLists() {
        return listService.getListRepository().findAll();
    }

    @GetMapping("/items")
    public Iterable<Item> getAllItems() {
        return itemService.getItemRepository().findAll();
    }

    @GetMapping("/{id}")
    public Optional<List> getOne(@PathVariable UUID id) {
        return listService.getListRepository().findById(id);
    }

    @GetMapping("/getItems/{id}")
    public Iterable<Item> getListItems(@PathVariable UUID id) {
        return itemService.getItemRepository().findByList(listService.getListRepository().findById(id).get());
    }

    @PostMapping
    public List createList(@RequestBody List list) {
        return listService.save(list);
    }

    @PostMapping("/addItem")
    public Optional<Item> createListAndAddItem(@RequestBody Item item) {
        return Optional.ofNullable(itemService.save(item));
    }

    @PutMapping("/addItem/{id}")
    public Optional<Item> addItemToList(@PathVariable UUID id, @RequestBody Item item) {
        return listService.addItemToList(id, item);
    }

    @PutMapping("/updateItem/{id}")
    public Optional<Item> updateItem(@PathVariable UUID id, @RequestBody Item item) {
        return itemService.update(id, item);
    }

    @PutMapping("/updateList/{id}")
    public Optional<List> updateList(@PathVariable UUID id, @RequestBody List list) {
        return listService.update(id, list);
    }

    @DeleteMapping("/list/{id}")
    public ResponseEntity<String> deleteList(@PathVariable UUID id) {
        listService.deleteList(id);
        return new ResponseEntity<String>("List deleted successfully",HttpStatus.OK);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable UUID id) {
        itemService.deleteById(id);
        return new ResponseEntity<String>("Item deleted successfully",HttpStatus.OK);
    }
}
