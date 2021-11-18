package com.example.eshop;

import com.example.eshop.repository.mongo.ProductMongoRepository;
import com.example.eshop.service.stream.StreamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class EshopApplicationTests {

    @Autowired
    private StreamService streamService;

    @Autowired
    private ProductMongoRepository productMongoRepository;

    @Test
    void getStreamCountTest() {
        long result = this.streamService.getManufacturerCount("AMD");

        assertEquals(1317, result);

    }

    @Test
    void streamMatchTest() {
        assertFalse(this.productMongoRepository.findAll()
                .stream().anyMatch(p -> p.getManufacturer().contains("3dfx")));

    }

    @Test
    void contextLoads() {
    }

}
