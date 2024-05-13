package com.doubledown.assignment.repositories;

import com.doubledown.assignment.models.CrawlerSetting;
import com.doubledown.assignment.models.Keyword;
import com.doubledown.assignment.models.NewsMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CrawlerSettingRepository  extends JpaRepository<CrawlerSetting, Long> {
    Set<CrawlerSetting> findAllByKeywordInAndNewsMediaIn(Set<Keyword> keywords, Set<NewsMedia> newsMedias);

    List<CrawlerSetting> findAllByNewsMedia(NewsMedia newsMedia);
}
