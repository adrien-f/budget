package com.adrienf.budget.domain;


import org.hibernate.annotations.GenericGenerator;
import org.joda.money.Money;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.UUID;

@Entity
public class Operation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String operation;

    @Column(nullable = false)
    private Money amount;

    @Column(nullable = false)
    private LocalTime createdOn;

    @Column(nullable = false)
    private LocalTime updatedOn;

    public Operation() {
    }

    public Operation(String operation, Money amount) {
        this.operation = operation;
        this.amount = amount;
    }

    public Operation(String operation, Money amount, LocalTime createdOn, LocalTime updatedOn) {
        this.operation = operation;
        this.amount = amount;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    @PrePersist
    protected void onCreate() {
        updatedOn = createdOn = LocalTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedOn = LocalTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public LocalTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
