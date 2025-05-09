package com.example.demo.security.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.admin.entity.AdminUser;

public class CustomUserDetails implements UserDetails {

    // TODO 最初の３つのprivate fieldの必要性を再考
    private final AdminUser adminUser;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(AdminUser adminUser) {
        this.adminUser = adminUser;
        this.firstName = adminUser.getFirstName();
        this.lastName = adminUser.getLastName();
        this.email = adminUser.getEmail();
        this.password = adminUser.getPassword();

        List<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        this.authorities = auths;

    }

    @Override
    public java.util.Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        // emailアドレスをユーザネームとして利用している(ログインのため)
        return this.email;
    }

    // 認証の有効状態に関するデフォルト実装
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
