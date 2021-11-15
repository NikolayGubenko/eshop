package com.example.eshop.mongo.entity;

import com.example.eshop.enums.ProductType;
import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "products")
public class ProductMongo {

    @Id
    private String id;

    @CsvBindByName(column = "Name")
    private String name;

    @CsvBindByName(column = "Architecture")
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductType productType = ProductType.GPU;

    private double price;

}
