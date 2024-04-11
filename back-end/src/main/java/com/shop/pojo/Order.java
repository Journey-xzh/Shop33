package com.shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Integer id; // 订单id

    private Integer pid;

    private Integer catid;

    private String name;

    private Integer price;

    private Integer quantity;
}
