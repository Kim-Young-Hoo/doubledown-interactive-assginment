package com.doubledown.assignment.controllers;


import com.doubledown.assignment.models.NewsMedia;
import com.doubledown.assignment.models.NewsMediaType;
import com.doubledown.assignment.services.NewsMediaService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news-media")
public class NewsMediaController {

    @Autowired
    private NewsMediaService newsMediaService;


    @PostConstruct
    void createNewsMedia() {
        newsMediaService.saveNewsMedia(NewsMediaType.NAVER);
        newsMediaService.saveNewsMedia(NewsMediaType.DAUM);
        newsMediaService.saveNewsMedia(NewsMediaType.GOOGLE);
    }

    @GetMapping
    public List<NewsMedia> getNewsMedias() {
        return newsMediaService.getNewsMedias();
    }


}
