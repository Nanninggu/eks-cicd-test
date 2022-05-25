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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class TestController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping("/sion")
    public String seung_hi(Model model) {
        model.addAttribute("nickname", "may9noy");
        return "goodbye";
    }

    @GetMapping("/sion/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id =" + id);

        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);

        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);

        return "articles/show";
    }

    @GetMapping("/sion/test_new")
    public String newArticleForm() {

        return "articles/test_new";
    }

    @PostMapping("/sion/create")
    public String createArticle(ArticleForm form) {
        System.out.println(form.toString());

        Article article = form.toEntity();
        log.info(form.toString());

        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

}
