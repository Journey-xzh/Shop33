package com.shop.service.impl;

import com.shop.mapper.LoginMapper;
import com.shop.pojo.User;
import com.shop.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;
    @Override
    public User login(String email, String password) {
        User user = loginMapper.login(email, password);
        return user;
    }

    @Override
    public void changePassword(String email, String newPassword) {
        loginMapper.changePassword(email, newPassword);
    }
}
