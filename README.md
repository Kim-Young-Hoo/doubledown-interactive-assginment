# 더블다운인터액티브 사전과제

--- 

# 1. 실행 방법

## 도커 있는 환경일 경우 (todo)

```agsl
$ docker-compose up -d
```

## 도커 없는 환경일 경우
- java 17+ required
```agsl
$ ./gradlew bootRun
```

# 2. 프로젝트 설명
## Web Framework
- Springboot

## Authentication
- Spring Security를 활용한 인증/인가 기능 구현
- JWT 방식의 간단한 로그인, 회원가입 기능

## Crawler
- @Scheduled를 사용한 크롤러 관리 
- 네이버 Open API를 활용하여 네이버 뉴스 수집
- Gnews 오픈소스를 활용하여 구글 뉴스 수집
- Daum 뉴스 검색 페이지 결과 html 파싱하여 수집

## Database
- Mysql 8 사용
- JPA를 활요한 ORM

## Frontend (TODO)

# 3. 데이터 모델링

# 4. API 명세서

# 5. 배포 방안 시나리오

