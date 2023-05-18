package com.example.Bank.fakeapistore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private Integer id;
    @JsonProperty("title")
    private String title;
    private BigDecimal price;
    private String description;
    private String category;
    private String image;
    private Rate rating;

}
