package com.doubledown.assignment.controllers;


import com.doubledown.assignment.models.CrawlerSetting;
import com.doubledown.assignment.models.News;
import com.doubledown.assignment.models.User;
import com.doubledown.assignment.repositories.NewsRepository;
import com.doubledown.assignment.services.CrawlerSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class NewsController {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CrawlerSettingService crawlerSettingService;

    @GetMapping("/user-news")
    public Page<News> getUserNews(Authentication authentication,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        User user = (User) authentication.getPrincipal();
        Set<CrawlerSetting> crawlerSettingsByUser = crawlerSettingService.getCrawlerSettings(user);

        Pageable pageable = PageRequest.of(page, size);
        return newsRepository.findAllByCrawlerSettingIn(crawlerSettingsByUser, pageable);
    }
}
