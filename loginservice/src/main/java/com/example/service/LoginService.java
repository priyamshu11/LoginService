package com.example.service;

import com.example.entity.UserEntity;
import com.example.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public Optional<UserEntity> findById(Long id) {
        return loginRepository.findById(id);
    }

    public List<UserEntity> findAllUsers() {
        return loginRepository.findAll();
    }

    public void registerUser(UserEntity user) {
        loginRepository.save(user);
    }

    public void updateUser(Long id, UserEntity updatedUser) {
        Optional<UserEntity> existingUser = loginRepository.findById(id);
        if (existingUser.isPresent()) {
            UserEntity user = existingUser.get();
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
                user.setPassword(updatedUser.getPassword());
            }

            loginRepository.save(user);
        }
    }

    public void deleteUser(Long id) {
        loginRepository.deleteById(id);
    }

    public Boolean validateCredentials(String username, String password) {
        UserEntity userOpt = loginRepository.findByUsername(username);
        if (userOpt != null) {
            return userOpt.getPassword().equals(password);
        }
        return false;
    }
}
