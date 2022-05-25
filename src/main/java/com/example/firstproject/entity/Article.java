package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

//@NoArgsConstructor // 디폴트 생성자를 추가해주는 어노테이션
@Entity //DB가 해당 객체를 인식 가능
@ToString
@AllArgsConstructor
@Getter
public class Article {

    // 기본적으로 Entity에는 대표값을 넣어줘야 한다.

    @Id // 사람으로 치면 주민등록 번호 같은 대표값 개념이다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동생성하는 어노테이션!
    private Long id;

    @Column // DB에서 이해할수 있도록 Column 이라는 어노테이션을 붙여준다.
    private String title;

    @Column
    private String content;

    public Article() {
    }

    public Long getId() {
        return id;
    }

    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
    }

//    @AllArgsConstructor 코드로 해당 코드를 갈음 함
//    // 생성자 추가
//
//    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }

//    @ToString 으로 해당 코드를 갈음 함
//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
}