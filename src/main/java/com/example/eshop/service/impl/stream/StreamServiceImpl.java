package com.example.eshop.service.impl.stream;

import com.example.eshop.enums.mongo.MemoryType;
import com.example.eshop.mongo.entity.ProductMongo;
import com.example.eshop.repository.mongo.ProductMongoRepository;
import com.example.eshop.service.stream.StreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;

@RequiredArgsConstructor
@Service
public class StreamServiceImpl implements StreamService {

    private final ProductMongoRepository productMongoRepository;

    @Override
    public List<ProductMongo> getFilteredStream() {
        return this.productMongoRepository.findAll()
                .stream().filter(p -> isPriceInRange(p.getPrice()))
                .filter(p -> p.getName().contains("GeForce")).limit(10).collect(Collectors.toList());
    }

    @Override
    public List<ProductMongo> getSortedStream() {
        final String manufacturer = "Nvidia";
        return this.productMongoRepository.findAll()
                .stream().filter(p -> manufacturer.equals(p.getManufacturer()))
                .filter(p -> p.getDescription().contains("Tesla"))
                .sorted(Comparator.comparing(ProductMongo::getPrice)).collect(Collectors.toList());
    }

    @Override
    public Long getManufacturerCount(String manufacturer) {
        return this.productMongoRepository.findAll()
                .stream().filter(p -> manufacturer.equals(p.getManufacturer())).count();
    }

    @Override
    public Map<MemoryType, List<ProductMongo>> groupByMemoryType() {
        return this.productMongoRepository.findAll()
                .stream().filter(p -> p.getMemoryType() != null)
                .collect(groupingBy((ProductMongo::getMemoryType)));
    }

    @Override
    public Map<Integer, Map<String, Double>> groupByYearAndManufacturer() {
        return this.productMongoRepository.findAll()
                .stream()
                .collect(groupingBy(p -> p.getReleaseDate().getYear(),
                        groupingBy(ProductMongo::getManufacturer, averagingDouble(ProductMongo::getPrice))));
    }


    private boolean isPriceInRange(double price) {
        return ((price > 500) && (price < 1000));
    }

}
