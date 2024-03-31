package com.shop.controller;

import com.shop.pojo.Category;
import com.shop.pojo.Result;
import com.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService; // 为什么创建的是接口的对象而不是实现类的对象？

    @GetMapping
    public Result<List<Category>> getAll() {
        List<Category> categories = categoryService.getAll();
        return Result.success(categories);
    }

    @PostMapping
    public Result insert(@RequestParam String name) {
        categoryService.insert(name);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam Integer catid) {
        categoryService.delete(catid);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Category category) {
        categoryService.update(category);
        return Result.success();
    }
}
