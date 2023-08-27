package com.example.auth.features.transaction.entities;

import com.example.auth.features.user.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Table(name = "transactions")
@Entity(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private LocalDateTime date;

    @Column
    private String type;

    @Column
    private String category;

    @Column
    private double value;

    @Column
    @ColumnDefault("true")
    @JsonIgnore
    private boolean active = true;

    @ManyToOne
    @JsonIgnore
    private User user;
}
