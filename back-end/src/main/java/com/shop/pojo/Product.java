package com.shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Integer pid;

    private Integer catid;

    private String name;

    private Integer price;

    private String url;
}
