package com.example.eshop.controller.stream;

import com.example.eshop.enums.mongo.MemoryType;
import com.example.eshop.mongo.entity.ProductMongo;
import com.example.eshop.service.stream.StreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/streams")
public class StreamRestController {

    private final StreamService streamService;

    @GetMapping("/filter")
    public List<ProductMongo> getFilteredData() {

        return this.streamService.getFilteredStream();
    }

    @GetMapping("/sort")
    public List<ProductMongo> getSortedData() {

        return this.streamService.getSortedStream();
    }

    @GetMapping("/count/{manufacturer}")
    public Long getManufacturerCount(@PathVariable String manufacturer) {

        return this.streamService.getManufacturerCount(manufacturer);
    }

    @GetMapping("/group-data")
    public Map<MemoryType, List<ProductMongo>> getGroupedData() {

        return this.streamService.groupByMemoryType();
    }

    @GetMapping("/multiple-group")
    public Map<Integer, Map<String, Double>> getMultipleGroupedData() {

        return this.streamService.groupByYearAndManufacturer();
    }

}
