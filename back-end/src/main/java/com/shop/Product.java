package com.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    Integer pid;

    Integer catid;

    String name;

    Integer price;

    String url;
}
