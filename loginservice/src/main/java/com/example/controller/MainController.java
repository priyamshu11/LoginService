package com.example.controller;


import com.example.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/c1")
public class MainController {
    @Autowired
    LoginService loginService;

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> f1(@PathVariable Long id){
        return new ResponseEntity<>(loginService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>> f2(){
        List<UserEntity> l1=new ArrayList<>();
        l1=loginService.findAllUsers();
        return ResponseEntity.ok().body(l1);
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserEntity> f3(){
        return ResponseEntity.ok(loginService.registerService());
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> f4(@RequestBody UserEntity userEntity){
        return ResponseEntity.ok(loginService.validateCredentials(userEntity.getId(),userEntity.getPassword()));
    }

}
