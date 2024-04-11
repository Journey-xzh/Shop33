package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private Integer pid;

    private Integer catid;

    private String name;

    private Integer price;

    private Integer quantity;

    private String url;
}
