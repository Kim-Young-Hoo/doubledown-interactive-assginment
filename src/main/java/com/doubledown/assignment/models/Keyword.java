package com.doubledown.assignment.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Keyword extends BaseEntity {
    private String word;

    @ManyToMany(mappedBy = "keywords")
    private Set<User> users = new HashSet<>();

}
