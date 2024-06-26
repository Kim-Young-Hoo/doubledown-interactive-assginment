package com.doubledown.assignment.crawlers;

import com.doubledown.assignment.models.CrawlerSetting;
import com.doubledown.assignment.models.News;
import com.doubledown.assignment.models.NewsMedia;
import com.doubledown.assignment.models.NewsMediaType;
import com.doubledown.assignment.responses.NaverNewsItem;
import com.doubledown.assignment.responses.NaverNewsResponse;
import com.doubledown.assignment.services.CrawlerSettingService;
import com.doubledown.assignment.services.NewsMediaService;
import com.doubledown.assignment.services.NewsService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;


/*
* naver api를 통해 크롤링 https://developers.naver.com/docs/serviceapi/search/news/news.md#%EB%89%B4%EC%8A%A4
* todo :
*  1. db에서 키워드 읽어서 query
*  2. display 100 한 뒤에 이미 insert한 거는 멈춤
*  3. 네이버 기사인 경우 링크 들어가서 본문 html 크롤링, 네이버 기사가 아닌 경우는 걍 포기하자
*   -> 그냥 요약 description 까지만 표출하고 기사 링크로
*  4. db insert
* */

@Service
public class NaverCrawler implements ICrawler {
    private static final Logger LOGGER = Logger.getLogger(NaverCrawler.class.getName());

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsMediaService newsMediaService;

    @Autowired
    private CrawlerSettingService crawlerSettingService;

    @Scheduled(fixedRate = 600000)
    public void startCrawl() {
        LOGGER.info("Starting crawl process...");
        String clientId = "AbgeKLpkyt5E6xwdDG8Z";
        String clientSecret = "0C0r1_cm2e";
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);

        NewsMedia newsMedia = newsMediaService.getNewsMedia(NewsMediaType.NAVER);
        List<CrawlerSetting> crawlerSettings = crawlerSettingService.getCrawlerSettings(newsMedia);

        for (CrawlerSetting crawlerSetting : crawlerSettings) {
            String apiURL = "https://openapi.naver.com/v1/search/news?query=" + crawlerSetting.getKeyword();
            String responseBody = getNews(apiURL, requestHeaders);
            LOGGER.info(responseBody);
            saveNews(responseBody, crawlerSetting);
        }

        LOGGER.info("Crawl process completed.");
    }

    @Override
    public String getNews() {
        return null;
    }

    public String getNews(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

    @Override
    public Boolean saveNews(String newsResponse, CrawlerSetting crawlerSetting) {

        Boolean isSuccessful = true;
        Gson gson = new Gson();
        NaverNewsResponse naverNewsResponse = gson.fromJson(newsResponse, NaverNewsResponse.class);
        for (NaverNewsItem newsItem : naverNewsResponse.getItems()) {
            System.out.print(newsItem);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

            try {
                LocalDateTime dateTime = LocalDateTime.parse(newsItem.getPubDate(), formatter);
                News news = new News(newsItem.getTitle(), newsItem.getDescription(), dateTime, newsItem.getOriginallink(), crawlerSetting);
                newsService.save(news);
            } catch (DateTimeParseException e) {
                e.printStackTrace();
                isSuccessful = false;
            }
        }
        return isSuccessful;
    }
}
