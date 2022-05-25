package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {

    private Long id;
    private String title;
    private String content;

//     @AllArgsConstructor 가 아래의 코드를 대체 함
//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

//    @ToString 이 아래의 코드를 대채 함
//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

    public Article toEntity() {
        return new Article(id, title, content); //toEntity타입은 Article을 반환하기를 원하기 때문에 새롭게 Article을 만들어서 리턴을 해준다.
        // Article이 Entity 클래스 였으므로,
        // Entity 클래스의 객체를 생성해야 하기 때문에 생성자를 호출해야 한다.
        // 해당 생성자는 id, title, content를 입력받고 있기 때문에 이것에 맞게 작성을 해야한다.
    }
}