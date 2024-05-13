package com.doubledown.assignment.crawlers;

import com.doubledown.assignment.models.CrawlerSetting;

/*
* google 뉴스 크롤러 https://github.com/ranahaani/GNews
* 오픈소스 있음 확인해보니 잘 작동은 함 2021년이 마지막 버전이긴 한데 아직 관리는 되고 있음
* 파이썬 라이브러리라서 파이썬 스크립트 통해서 실행시켜야 됨
* */
public class GoogleCrawler implements ICrawler{
    @Override
    public void startCrawl() {

    }

    @Override
    public String getNews() {
        return null;
    }

    @Override
    public Boolean saveNews(String news, CrawlerSetting crawlerSetting) {
        return null;
    }
}
