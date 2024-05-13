package com.doubledown.assignment.services;


import com.doubledown.assignment.models.NewsMedia;
import com.doubledown.assignment.models.NewsMediaType;
import com.doubledown.assignment.repositories.NewsMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsMediaService {

    @Autowired
    private NewsMediaRepository newsMediaRepository;

    public NewsMedia getNewsMedia(NewsMediaType newsMediaType) {
        return newsMediaRepository.findByNewsMediaType(newsMediaType);
    }


    public void saveNewsMedia(NewsMediaType newsMediaType) {
        newsMediaRepository.save(NewsMedia.builder().newsMediaType(newsMediaType).build());
    }

    public List<NewsMedia> getNewsMedias() {
        return newsMediaRepository.findAll();


    }
}
