package com.doubledown.assignment.services;


import com.doubledown.assignment.models.CrawlerSetting;
import com.doubledown.assignment.models.NewsMedia;
import com.doubledown.assignment.models.User;
import com.doubledown.assignment.repositories.CrawlerSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CrawlerSettingService {
    @Autowired
    private CrawlerSettingRepository crawlerSettingRepository;

    public List<CrawlerSetting> getCrawlerSettings(NewsMedia newsMedia) {
        return crawlerSettingRepository.findAllByNewsMedia(newsMedia);
    }
    public Set<CrawlerSetting> getCrawlerSettings(User user) {
        return crawlerSettingRepository.findAllByKeywordInAndNewsMediaIn(user.getKeywords(), user.getNewsMedias());
    }


}
