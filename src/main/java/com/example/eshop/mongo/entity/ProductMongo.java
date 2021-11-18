package com.example.eshop.mongo.entity;

import com.example.eshop.enums.ProductType;
import com.example.eshop.enums.mongo.MemoryType;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDate;

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

    @CsvBindByName(column = "Manufacturer")
    private String manufacturer;

    @Enumerated(EnumType.STRING)
    private ProductType productType = ProductType.GPU;

    @CsvCustomBindByName(column = "Release_Date",converter = LocalDateConverter.class)
    private LocalDate releaseDate;

    @CsvBindByName(column = "Memory_Type")
    @Enumerated(EnumType.STRING)
    private MemoryType memoryType;

    private double price;

}
