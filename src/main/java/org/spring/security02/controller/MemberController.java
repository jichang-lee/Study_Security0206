package org.spring.security02.controller;

import lombok.Getter;
import org.spring.security02.dto.MemberDto;
import org.spring.security02.entity.MemberEntity;
import org.spring.security02.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Member;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;


    @GetMapping("/join")
    public String joinView(Model model){

        model.addAttribute("memberDto",new MemberDto());
        return "member/join";
    }

    @PostMapping("/join")
    public String joinLogic(@Valid MemberDto memberDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "member/join";
        }

        memberService.insert(memberDto);

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String loginView(){

        return "member/login";
    }

    @GetMapping("/fail")
    public String faileView(){
        return "member/fail";
    }


    @GetMapping("/user/memberList")
    public String MemberList(Model model){
       List<MemberDto> memberDto = memberService.memberList();
       model.addAttribute("memberList",memberDto);

       return "member/list";
    }


    @GetMapping("/user/search")
    public String memberSearch(@RequestParam(value = "search",required = false)String search,
                               Model model){

        List<MemberDto> searchList = memberService.memberSearch(search);

        model.addAttribute("memberList",searchList);

        return "member/list";

    }

    @GetMapping("/user/detail/{id}")
    public String memberDetail(@PathVariable Long id , Model model){

        MemberDto memberDto= memberService.memberDetail(id);
        model.addAttribute("list",memberDto);

        return "/member/detail";
    }

    @GetMapping("/user/update/{id}")
    public String memberUpdateView(@PathVariable("id") Long id, Model model){


        model.addAttribute("list",memberService.memberDetail(id));
        return "/member/update";
    }

    @PostMapping("/user/update")
    public String memberUpdate(@ModelAttribute MemberDto memberDto){

        memberService.insert(memberDto);

        return "redirect:/member/user/memberList";
    }


    //page
    @GetMapping("/user/memberPage")
    public String memberPage(@PageableDefault(page =0, size = 3,sort = "id",direction = Sort.Direction.DESC)
                                 Pageable pageable,Model model){

        Page<MemberDto> memberDtos= memberService.memberPage(pageable);
        int blockNum=4;
        int nowPage=memberDtos.getNumber()+1;
        int startPage=Math.max(1,memberDtos.getNumber()-blockNum);
        int endPage=memberDtos.getTotalPages();

        model.addAttribute("memberPage",memberDtos);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

    return "/member/memberListPage";
    }



}
