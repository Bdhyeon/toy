package com.bdh.toy.login.controller;

import com.bdh.toy.login.dto.RegisterDTO;
import com.bdh.toy.login.service.MemberRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final MemberRegisterService memberRegisterService;

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("registerDTO", new RegisterDTO());
        return "login";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterDTO dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "login";
        }

        memberRegisterService.register(dto);

        return "/register";
    }
}
