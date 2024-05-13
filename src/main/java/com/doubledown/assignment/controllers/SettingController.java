package com.doubledown.assignment.controllers;


import com.doubledown.assignment.models.Keyword;
import com.doubledown.assignment.models.NewsMedia;
import com.doubledown.assignment.models.NewsMediaType;
import com.doubledown.assignment.models.User;
import com.doubledown.assignment.repositories.KeywordRepository;
import com.doubledown.assignment.repositories.NewsMediaRepository;
import com.doubledown.assignment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/setting")
public class SettingController {

    @Autowired
    private NewsMediaRepository newsMediaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @PostMapping("/media/{newsMediaTypeString}")
    public void addMedia(@PathVariable String newsMediaTypeString) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        NewsMediaType newsMediaType = NewsMediaType.valueOf(newsMediaTypeString);

        Optional<NewsMedia> existingNewsMedia = user.getNewsMedias().stream()
                .filter(media -> media.getNewsMediaType().equals(newsMediaType))
                .findFirst();

        if (existingNewsMedia.isEmpty()) {
            NewsMedia newsMedia = newsMediaRepository.findByNewsMediaType(newsMediaType);
            user.getNewsMedias().add(newsMedia);
            newsMediaRepository.save(newsMedia);
            userRepository.save(user);
        }
    }

    @PostMapping("/keyword/{keywordString}")
    public void addKeyword(@PathVariable String keywordString) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Keyword> existingKeywordSetting = user.getKeywords().stream()
                .filter(keyword -> keyword.getWord().equals(keywordString))
                .findFirst();

        if (existingKeywordSetting.isEmpty()) {

        }
        return keywordRepository.save(keyword);
    }


}
