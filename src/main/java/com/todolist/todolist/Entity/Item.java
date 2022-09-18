package com.todolist.todolist.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "item")
public class Item implements Serializable {
    private static final long serialVersionUID = 3518094050530415539L;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"name", "timeCreated", "timeModified"})
    private List list;
}