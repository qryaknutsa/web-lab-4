package com.example.weblab4.controller;

import com.example.weblab4.entity.MyUser;
import com.example.weblab4.repo.MyUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {
    private final MyUserRepo myUserRepo;
    private final MessageDigest md = MessageDigest.getInstance("SHA-512");

    @Autowired
    public UserController(MyUserRepo myUserRepo) throws NoSuchAlgorithmException {
        this.myUserRepo = myUserRepo;
    }

    @PostMapping("{login}")
    public String isExist(@RequestBody MyUser reqUser, @PathVariable String login) {
        MyUser realUser;
        String toSend = "";
        try {
            realUser = myUserRepo.findAll().stream().filter(user -> user.getLogin().equals(login)).findFirst().get();
            String reqPass = encryptPassword(reqUser.getPassword());
            if (realUser.getPassword().equals(reqPass)) toSend = "pass";
            else toSend = "Неправильный пароль.";
        } catch (NoSuchElementException e) {
            toSend = "Пользователя с таким логином не существует.";
        }
        return toSend;
    }

    @PostMapping
    public String create(@RequestBody MyUser myUser) {
        String toSend = "";
        try {
            myUserRepo.findAll().stream().filter(user -> user.getLogin().equals(myUser.getLogin())).findFirst().get();
            toSend = "Пользователь с таким логином уже существует.";
        } catch (NoSuchElementException e) {
            myUser.setPassword(encryptPassword(myUser.getPassword()));
            myUserRepo.save(myUser);
            toSend = "Вы успешно зарегистрированы. Войдите в свой аккаунт.";
        }
        return toSend;
    }


    private String encryptPassword(final String password){
        md.update(password.getBytes());
        byte[] byteBuffer = md.digest();
        StringBuilder strHexString = new StringBuilder();

        for (int i = 0; i < byteBuffer.length; i++) {
            String hex = Integer.toHexString(0xff & byteBuffer[i]);
            if (hex.length() == 1) {
                strHexString.append('0');
            }
            strHexString.append(hex);
        }
        return strHexString.toString();
    }


}
