package com.doubledown.assignment.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class CrawlerSetting extends BaseEntity{

    @Column(nullable = false)
    private String newsMedia;

    @Column(nullable = false)
    private String keyword;

    @OneToMany(mappedBy = "crawlerSetting", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<News> news = new HashSet<>();
}

