package com.todolist.todolist.Repository;

import com.todolist.todolist.Entity.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ListRepository extends JpaRepository<List, UUID> {
}