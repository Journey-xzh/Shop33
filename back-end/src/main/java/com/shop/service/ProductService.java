package com.shop.service;

import com.shop.dto.PaymentDTO;
import com.shop.pojo.Order;
import com.shop.pojo.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> userQuery();

    Product getByPid(Integer pid);

    List<Product> getByCatid(Integer catid);

    List<Product> adminQuery();

    void adminInsert(MultipartFile file, Integer catid, String name, Integer price) throws IOException;

    void adminDelete(Integer pid);

    void adminUpdate(Integer pid, Integer catid, String name, Integer price, MultipartFile file) throws IOException;

    List<Product> touristQuery();

    Product touristGetByPid(Integer pid);

    List<Product> touristGetByCatid(Integer catid);

    void userPayment(PaymentDTO[] paymentDTOS);

    List<Order> getOrder();
}
