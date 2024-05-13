package com.doubledown.assignment.models;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;


@Entity
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class News extends BaseEntity{

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDateTime publishedDate;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "crawler_setting_id")
    private CrawlerSetting crawlerSetting;

}
