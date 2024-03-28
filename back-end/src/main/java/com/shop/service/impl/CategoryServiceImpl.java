package com.shop.service.impl;

import com.shop.Category;
import com.shop.mapper.CategoryMapper;
import com.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> getAll() {
        List<Category> categories = categoryMapper.getAll();
        return categories;
    }

    public void insert(String name) {
        Integer maxId = categoryMapper.getMaxId();
        categoryMapper.insert(maxId + 1, name);
    }

    public void delete(Integer catid) {
        categoryMapper.delete(catid);
    }


    public void update(Category category) {
        categoryMapper.update(category);
    }

}
