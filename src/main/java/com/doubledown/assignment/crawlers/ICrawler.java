package com.doubledown.assignment.crawlers;

import com.doubledown.assignment.models.CrawlerSetting;

public interface ICrawler {
    public void startCrawl();
    public String getNews();
    public Boolean saveNews(String result, CrawlerSetting crawlerSetting);

}
