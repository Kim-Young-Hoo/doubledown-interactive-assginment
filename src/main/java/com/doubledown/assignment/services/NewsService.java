package com.doubledown.assignment.services;


import com.doubledown.assignment.models.News;
import com.doubledown.assignment.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;


    public News save(News news) {
        News save = newsRepository.save(news);
        return save;

    }
}
