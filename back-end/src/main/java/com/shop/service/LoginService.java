package com.shop.service;

import com.shop.pojo.User;

public interface LoginService {
    User login(String email, String password);

    void changePassword(String email, String newPassword);
}
