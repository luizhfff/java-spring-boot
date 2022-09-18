package com.todolist.todolist.Service;

import com.todolist.todolist.Entity.Item;
import com.todolist.todolist.Entity.List;
import com.todolist.todolist.Repository.ItemRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {
  @Autowired
  @Getter
  private ItemRepository itemRepository;
  @Autowired
  private ListService listService;

  public long deleteByList(List list) {
    return itemRepository.deleteByList(list);
  }

  public Iterable<Item> findByList(List list) {
    return itemRepository.findByList(list);
  }

  public Item save(Item item) {
    item.setTimeCreated(new Date());
    item.setTimeModified(new Date());
    if(item.getList() == null) {
      List newList = new List();
      newList.setName("New list " + new Date());
      List savedRecord = listService.save(newList);
      item.setList(savedRecord);
      return itemRepository.save(item);
    }
    return itemRepository.save(item);
  }

  public Optional<Item> update(UUID id, Item newItem) {
    return itemRepository.findById(id).map(item->{
      item.setTimeModified(new Date());
      item.setText(newItem.getText());
      return itemRepository.save(item);
    });
  }

  public void deleteById(UUID id) {
    itemRepository.deleteById(id);
  }
}
