package com.example.controller;


import com.example.entity.UserEntity;
import com.example.service.LoginService;
import org.hibernate.query.UnknownSqlResultSetMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/c1")
public class MainController {
    @Autowired
    LoginService loginService;

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> f1(@PathVariable Long id){
        Optional<UserEntity> user=loginService.findById(id);
        if(user.isPresent()){
            return new ResponseEntity<>(user.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>> f2(){
        List<UserEntity> l1=new ArrayList<>();
        l1=loginService.findAllUsers();
        return ResponseEntity.ok().body(l1);
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> f3(@RequestBody UserEntity userEntity)
    {
        loginService.registerUser(userEntity);
        return new ResponseEntity<>("Created Successfully",HttpStatus.OK);

    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> f4(@RequestBody UserEntity userEntity){
        return ResponseEntity.ok(loginService.validateCredentials(userEntity.getUsername(),userEntity.getPassword()));
    }

}
