package com.example.security.service;

import com.example.security.entity.Account;
import com.example.security.payload.auth.AccountViewDTO;
import com.example.security.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

      private final AccountRepository accountRepository;
      private final PasswordEncoder passwordEncoder;

      public Account save(Account account){
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            if(account.getRole()==null){
                  account.setRole("ROLE_USER");
            }
            return accountRepository.save(account);
      }

      public List<AccountViewDTO> findAll(){
          List<Account> accounts=accountRepository.findAll();
          return accounts.stream().map(account -> {
              return  new AccountViewDTO(account.getId(), account.getEmail(), account.getRole()) ;
          }).toList();
      }

      @Override
      public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
           Optional<Account> optionalAccount=accountRepository.findByEmail(email);
           if(!optionalAccount.isPresent()){
                 throw new UsernameNotFoundException("Account not found");
           }
           Account account=optionalAccount.get();
           // GrantedAuthority : 권한과 관련된 클래스
           List<GrantedAuthority> grantedAuthority=new ArrayList<>();
           grantedAuthority.add(new SimpleGrantedAuthority(account.getRole()));
            return new User(account.getEmail(), account.getPassword(), grantedAuthority);
      }
}
