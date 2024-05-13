package com.doubledown.assignment.repositories;

import com.doubledown.assignment.models.NewsMedia;
import com.doubledown.assignment.models.NewsMediaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NewsMediaRepository extends JpaRepository<NewsMedia, Long> {

    NewsMedia findByNewsMediaType(NewsMediaType name);


}
