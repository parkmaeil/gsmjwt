package com.example.security.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Account { // [인증 + 권한]
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email; // 사용자아이디
    private String password; // 암호화
    private String role; // 권한 : SCOPE_ROLE_USER, SCOPE_ROLE_ADMIN, SCOPE_ROLE_MANAGER

}
