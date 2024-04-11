package com.shop.controller;

import com.shop.dto.PaymentDTO;
import com.shop.pojo.Order;
import com.shop.pojo.Product;
import com.shop.pojo.Result;
import com.shop.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    // --------user---------
    @GetMapping("/user")
    public Result<List<Product>> userQuery() {
        List<Product> products = productService.userQuery();
        return Result.success(products);
    }

    @GetMapping("/user/product")
    public Result<Product> getByPid(@RequestParam Integer pid) {
        Product product = productService.getByPid(pid);
        return Result.success(product);
    }

    @GetMapping("/user/category")
    public Result<List<Product>> getByCatid(@RequestParam Integer catid) {
        List<Product> products = productService.getByCatid(catid);
        return Result.success(products);
    }

    @PostMapping("/user/payment")
    public Result userPayment (@RequestBody PaymentDTO[] paymentDTOS) {
        productService.userPayment(paymentDTOS);
        return Result.success();
    }

    @GetMapping("/user/order")
    public  Result<List<Order>> getOrder() {
        List<Order> orders = productService.getOrder();
        return Result.success(orders);
    }

    // --------tourist---------
    @GetMapping("/tourist")
    public Result<List<Product>> touristQuery() {
        List<Product> products = productService.touristQuery();
        return Result.success(products);
    }

    @GetMapping("/tourist/product")
    public Result<Product> touristGetByPid(@RequestParam Integer pid) {
        Product product = productService.touristGetByPid(pid);
        return Result.success(product);
    }

    @GetMapping("/tourist/category")
    public Result<List<Product>> touristGetByCatid(@RequestParam Integer catid) {
        List<Product> products = productService.touristGetByCatid(catid);
        return Result.success(products);
    }

    // --------admin---------
    @GetMapping("/admin/product")
    public Result<List<Product>> adminQuery() {
        List<Product> products = productService.adminQuery();
        return Result.success(products);
    }

    @PostMapping("/admin/product")
    public Result adminInsert(@RequestParam("catid") Integer catid,
                              @RequestParam("name") String name,
                              @RequestParam("price") Integer price,
                              @RequestParam("file") MultipartFile file) throws IOException {
        productService.adminInsert(file, catid, name, price);
        return Result.success();
    }

    @DeleteMapping("/admin/product")
    public Result adminDelete(@RequestParam Integer pid) {
        productService.adminDelete(pid);
        return Result.success();
    }

    @PutMapping("/admin/product")
    public Result adminUpdate(@RequestParam("pid") Integer pid,
                              @RequestParam("catid") Integer catid,
                              @RequestParam("name") String name,
                              @RequestParam("price") Integer price,
                              @RequestParam("file") MultipartFile file) throws IOException {
        productService.adminUpdate(pid, catid, name, price, file);
        return Result.success();
    }
}
