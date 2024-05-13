package com.doubledown.assignment.repositories;

import com.doubledown.assignment.models.CrawlerSetting;
import com.doubledown.assignment.models.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    Page<News> findAllByCrawlerSettingIn(Set<CrawlerSetting> crawlerSettingsByUser, Pageable pageable);

}
