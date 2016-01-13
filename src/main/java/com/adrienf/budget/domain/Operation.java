package com.adrienf.budget.domain;


import com.adrienf.budget.json.MoneyDeserializer;
import com.adrienf.budget.json.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.GenericGenerator;
import org.joda.money.Money;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Operation implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum OperationType {CB, PRLV, VIR, WITHDRAW}

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    @JsonDeserialize(using = MoneyDeserializer.class)
    @JsonSerialize(using = MoneySerializer.class)
    private Money amount;

    @Column(nullable = false)
    private LocalDateTime eventDate;

    @Column(nullable = false)
    private OperationType type;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @Column(nullable = false)
    private LocalDateTime updatedOn;

    public Operation() {
    }

    public Operation(String label, Money amount, OperationType type, LocalDateTime eventDate) {
        this.label = label;
        this.amount = amount;
        this.eventDate = eventDate;
        this.type = type;
    }

    public Operation(String label, Money amount, OperationType type, LocalDateTime eventDate, LocalDateTime createdOn, LocalDateTime updatedOn) {
        this.label = label;
        this.amount = amount;
        this.eventDate = eventDate;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    @PrePersist
    protected void onCreate() {
        updatedOn = createdOn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedOn = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
