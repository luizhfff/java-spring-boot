package com.todolist.todolist.Repository;

import com.todolist.todolist.Entity.Item;
import com.todolist.todolist.Entity.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

  @Transactional
  long deleteByList(List list);

  Iterable<Item> findByList(List list);
}