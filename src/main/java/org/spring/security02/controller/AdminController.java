package org.spring.security02.controller;

import org.spring.security02.dto.MemberDto;
import org.spring.security02.repository.MemberRepository;
import org.spring.security02.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/list")
    public String listView(Model model){
         List<MemberDto> list= memberService.memberList();

         model.addAttribute("list",list);

        return "/admin/memberList";
    }

}
