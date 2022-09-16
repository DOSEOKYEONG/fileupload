package com.ll.exam.app10.app.home.controller;

import com.ll.exam.app10.app.member.entity.Member;
import com.ll.exam.app10.app.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomerController {
    private final MemberService memberService;

    @RequestMapping("/")
    public String main(Principal principal, Model model) {
        if (principal != null && principal.getName() != null) {
            Member member = memberService.getMemberByUsername(principal.getName());
            model.addAttribute("member", member);
            String memberProfileImgUrl = member.getProfileImgUrl();
            model.addAttribute("memberProfileImgUrl", memberProfileImgUrl);
        }

        return "home/main";
    }

    @RequestMapping("/test/upload")
    public String upload() {
        return "home/test/upload";
    }
}
