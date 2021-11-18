package com.example.eshop.service.stream;

import com.example.eshop.enums.mongo.MemoryType;
import com.example.eshop.mongo.entity.ProductMongo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface StreamService {

    List<ProductMongo> getFilteredStream();

    List<ProductMongo> getSortedStream();

    Long getManufacturerCount(String manufacturer);

    Map<MemoryType, List<ProductMongo>> groupByMemoryType();

    Map<Integer, Map<String, Double>> groupByYearAndManufacturer();

}
