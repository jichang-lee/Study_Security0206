package org.spring.security02.controller;

import lombok.Getter;
import org.spring.security02.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @GetMapping({"/",""})
    public String mainView(){

        return "main";


    }
}
