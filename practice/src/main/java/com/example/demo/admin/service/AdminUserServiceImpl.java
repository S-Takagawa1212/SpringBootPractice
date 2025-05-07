package com.example.demo.admin.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.admin.data.AdminUserData;
import com.example.demo.admin.entity.AdminUser;
import com.example.demo.admin.form.signInAdminUserForm;
import com.example.demo.admin.repository.AdminUserRepository;
import com.example.demo.contact.form.RegistAdminUserForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminUserServiceImpl implements AdminUserService {
    private final AdminUserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void saveAdminUser(RegistAdminUserForm form) {
        // TODO form→dto→entityの流れのなかでpasswordを暗号化する。その後repository.save()で保存する
        AdminUserData data = mapFormToData(form);
        AdminUser user = mapDataToEntity(data);
        repository.save(user);
    }

    private AdminUserData mapFormToData(RegistAdminUserForm form) {

        AdminUserData data = new AdminUserData(
                null,
                form.getLastName(),
                form.getFirstName(),
                form.getEmail(),
                form.getPassword(),
                null,
                null,
                null);

        return data;
    }

    private AdminUser mapDataToEntity(AdminUserData data) {
        // ここでデータ詰め替え時にpasswordを暗号化したい
        AdminUser adminUser = new AdminUser(
                data.getId(),
                data.getLastName(),
                data.getFirstName(),
                data.getEmail(),
                passwordEncoder.encode(data.getPassword()), // 暗号化ここ！
                data.getCurrentSignInAt(),
                data.getCreatedAt(),
                data.getUpdatedAt());

        return adminUser;
    }

    @Override
    public AdminUser findAdminUserByEmail(signInAdminUserForm signInAdminUserForm) {

        Optional<AdminUser> optionalUser = repository.findByEmail(signInAdminUserForm.getEmail());
        AdminUser user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("このメールアドレスは登録されていません。"));

        return user;
    }

}
