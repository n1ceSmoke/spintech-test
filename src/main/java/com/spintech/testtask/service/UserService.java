package com.spintech.testtask.service;

import com.spintech.testtask.entity.User;

import java.util.List;

public interface UserService {
    User registerUser(String email, String password);
    User findUser(String email, String password);
    User addToFavorites(String email, String password, List<Long> actorsIds);
}

