package com.adrienf.budget.domain;


import com.adrienf.budget.json.MoneyDeserializer;
import com.adrienf.budget.json.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
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
    @Column()
    @Type(type="pg-uuid")
    private UUID id;

    @Column(nullable = false)
    private String remark;

    @Column(nullable = false)
    @JsonDeserialize(using = MoneyDeserializer.class)
    @JsonSerialize(using = MoneySerializer.class)
    private Money amount;

    @Column(nullable = false)
    private LocalDateTime executionDate;

    @Column(nullable = true)
    private Company company;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @Column(nullable = false)
    private LocalDateTime updatedOn;

    public Operation() {
    }

    public Operation(String remark, Money amount, LocalDateTime executionDate) {
        this.remark = remark;
        this.amount = amount;
        this.executionDate = executionDate;
    }

    public Operation(String remark, Money amount, LocalDateTime executionDate, LocalDateTime createdOn, LocalDateTime updatedOn) {
        this.remark = remark;
        this.amount = amount;
        this.executionDate = executionDate;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public LocalDateTime getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDateTime executionDate) {
        this.executionDate = executionDate;
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
