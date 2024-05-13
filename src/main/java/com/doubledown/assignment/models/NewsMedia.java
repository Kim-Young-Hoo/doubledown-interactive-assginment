package com.doubledown.assignment.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class NewsMedia extends BaseEntity{

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NewsMediaType newsMediaType;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "newsMedias")
    @Builder.Default
    private Set<User> users = new HashSet<>();

    public void addUser(User user) {
        this.users.add(user);
    }

}
