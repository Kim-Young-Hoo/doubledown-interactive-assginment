package com.doubledown.assignment.crawler;


/*
* daum news crawler
* 다음카카오는 api 제공하지 않음, 오픈소스도 없음
*
* https://search.daum.net/search?w=news&cluster=y&cluster_page=1&q=%EC%84%A0%EA%B1%B0&sort=recency&DA=PGD&p=1
* 이 주소로 최신순 정렬 pagination 타고 들어가서 html 파싱하는 수 밖에 없음
* title : .item-title > .tit-g clamp-g > a.text
* description : .item-contents > .conts-desc .clamp-g2 > a.text
* */
public class DaumCrawler {
}
