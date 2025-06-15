package com.sg.kata.entity;

import com.sg.kata.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "operationId", nullable = false, unique = true)
    private Integer operationId;
    @Enumerated(EnumType.STRING)
    private OperationType operation;
    private LocalDateTime dateOperation;
    private double amount;

    @Override
    public String toString() {
        return "Operation{" +
                "operationId=" + operationId +
                ", operation=" + operation +
                ", dateOperation=" + dateOperation +
                ", amount=" + amount +
                '}';
    }
}
