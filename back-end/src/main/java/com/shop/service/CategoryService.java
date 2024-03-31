package com.shop.service;

import com.shop.pojo.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    void insert(String name);

    void delete(Integer catid);

    void update(Category category);
}
