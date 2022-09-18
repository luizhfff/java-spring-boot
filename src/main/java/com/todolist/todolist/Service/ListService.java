package com.todolist.todolist.Service;

import com.todolist.todolist.Entity.Item;
import com.todolist.todolist.Entity.List;
import com.todolist.todolist.Repository.ListRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class ListService {
  @Autowired
  @Getter
  private ListRepository listRepository;
  @Autowired
  @Lazy
  private ItemService itemService;

  public List save(List list) {
    list.setTimeCreated(new Date());
    list.setTimeModified(new Date());
    return listRepository.save(list);
  }

  public Optional<List> update(UUID id, List newList) {
    return listRepository.findById(id).map(list->{
      list.setTimeModified(new Date());
      list.setName(newList.getName());
      return listRepository.save(list);
    });
  }

  public Optional<Item> addItemToList(UUID id, Item item) {
    return listRepository.findById(id).map(list->{
      item.setList(list);
      return itemService.save(item);
    });
  }

  public void deleteList(UUID id) {
    listRepository.findById(id).map(list->{
      itemService.deleteByList(list);
      listRepository.deleteById(list.getId());
      return null;
    });
  }
}
