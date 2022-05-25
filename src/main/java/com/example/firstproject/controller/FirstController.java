package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi") //접속 가능한 url을 명시
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "짱구"); //모델은 짱구라는 이름을 username에 맵핑시켜서 보내주는 역할을 한다.
        return "greetings"; // templates/greetings.mustache -> 브라우저로 전송!
    }

    @GetMapping("/bye") //접속 가능한 url을 명시
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "may9noy"); //모델은 짱구라는 이름을 username에 맵핑시켜서 보내주는 역할을 한다.
        return "goodbye"; // templates/greetings.mustache -> 브라우저로 전송!
    }

    @GetMapping("/seung") //접속 가능한 url을 명시
    public String seung_hi(Model model) {
        model.addAttribute("nickname", "may9noy"); //모델은 짱구라는 이름을 username에 맵핑시켜서 보내주는 역할을 한다.
        return "goodbye"; // templates/greetings.mustache -> 브라우저로 전송!
    }
}