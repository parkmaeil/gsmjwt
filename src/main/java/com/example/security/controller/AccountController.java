package com.example.security.controller;

import com.example.security.service.AccountService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/")
    public String demo(){
        return "Hello world";
    }


    @Tag(name = "Test", description = "The Test API.")
    @SecurityRequirement(name = "security-demo-api")
    @GetMapping("/test")
    public String test(){
        return "Test Api";
    }
}
