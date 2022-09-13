package com.todolist.todolist.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "to_do_item")
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(nullable = false)
    private Date timeCreated;

    @Column(nullable = false)
    private Date timeModified;

}