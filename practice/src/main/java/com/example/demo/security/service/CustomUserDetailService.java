package com.example.demo.security.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.admin.entity.AdminUser;
import com.example.demo.admin.repository.AdminUserRepository;
import com.example.demo.security.config.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final AdminUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // emailで検索したAdminUserをUSerDetails型として返す。
        Optional<AdminUser> optionalUser = repository.findByEmail(email);
        AdminUser user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("このメールアドレスは登録されていません。"));

        return new CustomUserDetails(user);
    }

}
