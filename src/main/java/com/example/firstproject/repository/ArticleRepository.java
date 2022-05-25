package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    //ArticleRepository는 CrudRepository가 가지고 있는 서비스를 정의 없이 바로 사용이 가능하다.
    //CrudRepository는 2개의 값이 필요하다. 관리대상엔티티, 관라대상 엔티티에서 정한 대표 타입을 지정 id 타입이 Long 이었기 때문에 Long을 지정

    @Override
    ArrayList<Article> findAll();
}
