package com.sg.kata.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "accountNumber", nullable = false, unique = true)
    private Integer accountNumber;
    @Column
    private Double balance;
    @OneToMany(targetEntity=Operation.class,cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Operation> operationsList;

    public BankAccount() {
        this.balance = 0.0;
        this.operationsList = new ArrayList<>();
    }
}
