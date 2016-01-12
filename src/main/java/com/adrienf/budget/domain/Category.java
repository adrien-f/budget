package com.adrienf.budget.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
public class Category {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String icon;

    @Column(nullable = false)
    private String color;

    @ManyToOne
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Collection<Category> children;

    public boolean isParent() {
        return parent == null;
    }

    public Category() {
    }
}
