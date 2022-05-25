package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j //로깅을 위한 어노테이션
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해 높은 객체를 가져다가 자동 연결!
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new") //url 맵핑
    public String newArticleForm() {

        return "articles/new"; //new.mustache 파일의 경로를 삽입
    }

    @PostMapping("/articles/create") //form에서 던진 action 주소이다.
    public String createArticle(ArticleForm form) {
        System.out.println(form.toString());

        //1. Dto를 Entity로 변환해야 한다.
        Article article = form.toEntity();

        log.info(form.toString());
//        System.out.println(form.toString()); → 로깅 기능으로 대체한다.

        //2. Repository에게 Entity를 DB안에 저장하게 한다.
        Article saved = articleRepository.save(article); // articleRepository가 save라는 메소드를 호출할 것인데
        // 위에서 생성한 article을 save 하게 할 것이다. 그리고 save 된 데이터를 최종적으로 반환하게 한다.
        // Article saved 라는 타입으로 반환한다.
        log.info(saved.toString());
//        System.out.println(saved.toString()); → 로깅 기능으로 대체한다.

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id =" + id);

//        1. id로 데이터를 가져온다.
//        Article타입의 articleEntity라는 이름으로 데이터를 받아 온다.
//        해당 id 값을 찾았는데 만약 값이 없다면 null을 반환해라 라는 의미.
//        그래서 articleEntity라는 변수에는 값이 있거나 없으면 null 이라는 변수가 담기게 된다.
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);

//        2. 가져온 데이터를 모델에 등록한다.
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);
//        3. 보여줄 페이지를 설정한다.
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {

//        1. 모든 Article을 가져온다.
        List<Article> articleEntityList = articleRepository.findAll();

//        2. 가져온 Article 묶음을 뷰로 전달한다.
        model.addAttribute("articleList", articleEntityList);
//
//        3. 뷰 페이지를 설정한다.
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {

        // 수정할 데이터를 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터를 등록하기
        model.addAttribute("article", articleEntity);

        // 뷰페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update") //update.mustache에서 던진 값
    public String update(ArticleForm form) {
        log.info(form.toString());

        // 1.DTO를 엔티티로 변환한다.
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 2.엔티티를 DB로 저장한다.

        //2-1:DB에서 기존 데이터를 가져온다.
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        //2-2:기존 데이터가 있다면!!, 값을 갱신한다.
        if (target != null) {
            articleRepository.save(articleEntity); //엔티티가 DB로 갱신 된다.
        }

        // 3.수정 결과 페이지로 리 다이렉트 한다.
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다!!");

        // 1. 삭제 대상을 가져온다.
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2. 대상을 삭제한다.
        if (target != null) {
            System.out.println(target.toString());
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료 되었습니다.");
        }

        // 3. 결과 페이지로 리 다이렉트 한다.
        return "redirect:/articles";
    }

}