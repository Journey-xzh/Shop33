package com.shop.service.impl;

import com.shop.pojo.Product;
import com.shop.mapper.ProductMapper;
import com.shop.service.ProductService;
import com.shop.utils.AliOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private AliOssUtil aliOssUtil;

    @Override
    public List<Product> userQuery() {
        List<Product> products = productMapper.userQuery();
        return products;
    }

    @Override
    public Product getByPid(Integer pid) {
        Product product = productMapper.getByPid(pid);
        return product;
    }

    @Override
    public List<Product> getByCatid(Integer catid) {
        List<Product> products = productMapper.getByCatid(catid);
        return products;
    }

    @Override
    public List<Product> adminQuery() {
        List<Product> products = productMapper.adminQuery();
        return products;
    }

    @Override
    public void adminInsert(MultipartFile file, Integer catid, String name, Integer price) throws IOException {
        String url = aliOssUtil.upload(file);

        Product product = new Product();
        Integer maxId = productMapper.getMaxId();
        product.setPid(maxId + 1);
        product.setCatid(catid);
        product.setName(name);
        product.setPrice(price);
        product.setUrl(url);

        productMapper.adminInsert(product);
    }

    @Override
    public void adminDelete(Integer pid) {
        productMapper.adminDelete(pid);
    }

    @Override
    public void adminUpdate(Integer pid, Integer catid, String name, Integer price, MultipartFile file) throws IOException {
        String url = aliOssUtil.upload(file);

        Product product = new Product();
        product.setPid(pid);
        product.setCatid(catid);
        product.setName(name);
        product.setPrice(price);
        product.setUrl(url);

        productMapper.adminUpdate(product);
    }

}
