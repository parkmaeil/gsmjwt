package com.example.security.config;

import com.example.security.entity.Account;
import com.example.security.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@RequiredArgsConstructor
public class SeedData implements CommandLineRunner {

     private final AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
             Account account01=new Account();
             Account account02=new Account();

             account01.setEmail("bitcocom@empas.com");
             account01.setPassword("password");
             account01.setRole("ROLE_USER");


            account02.setEmail("bit@empas.com");
            account02.setPassword("password");
            account02.setRole("ROLE_ADMIN");

            accountService.save(account01);
            accountService.save(account02);

    }
}
