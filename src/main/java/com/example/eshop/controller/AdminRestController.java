package com.example.eshop.controller;

import com.example.eshop.dto.AdminOrderDTO;
import com.example.eshop.dto.PostalOfficeDTO;
import com.example.eshop.dto.ProductDTO;
import com.example.eshop.entity.Order;
import com.example.eshop.entity.PostalOffice;
import com.example.eshop.entity.Product;
import com.example.eshop.mapper.OrderMapper;
import com.example.eshop.mapper.PostalOfficeMapper;
import com.example.eshop.mapper.ProductMapper;
import com.example.eshop.service.AdminService;
import com.example.eshop.service.PostalOfficeService;
import com.example.eshop.service.ProductService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/admin")
public class AdminRestController {

    private final AdminService adminService;

    private final ProductService productService;

    private final PostalOfficeService postalOfficeService;

    private final OrderMapper orderMapper;

    private final ProductMapper productMapper;

    private final PostalOfficeMapper postalOfficeMapper;

    @GetMapping("/orders")
    public Page<AdminOrderDTO> findAllOrders(Pageable pageable) {
        Page<Order> pageOrders = adminService.getAllOrders(pageable);
        List<AdminOrderDTO> orderDTOList = orderMapper.toAdminOrderDTOList(pageOrders.getContent());
        return new PageImpl<>(orderDTOList, pageable, pageOrders.getTotalElements());
    }

    @GetMapping("/products")
    public Page<ProductDTO> findAllProducts(Pageable pageable) {
        Page<Product> pageProducts = productService.getAllProducts(pageable);
        List<ProductDTO> productDTOList = productMapper.toProductDTOList(pageProducts.getContent());
        return new PageImpl<>(productDTOList, pageable, pageProducts.getTotalElements());
    }

    @GetMapping("/postal-offices")
    public Page<PostalOfficeDTO> findAllPostalOffices(Pageable pageable) {
        Page<PostalOffice> pagePostalOffices = postalOfficeService.getAllPostalServices(pageable);
        List<PostalOfficeDTO> postalOfficeDTOList = postalOfficeMapper.toPostalOfficeDTO(pagePostalOffices.getContent());
        return new PageImpl<>(postalOfficeDTOList, pageable, pagePostalOffices.getTotalElements());
    }

    @PostMapping("/products")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        this.productService.saveProduct(this.productMapper.toProduct(productDTO));
        return productDTO;
    }

    @PostMapping("/postal-offices")
    public PostalOfficeDTO addPostalOffice(@RequestBody PostalOfficeDTO postalOfficeDTO) {
        this.postalOfficeService.savePostalOffice(this.postalOfficeMapper.toPostalOffice(postalOfficeDTO));
        return postalOfficeDTO;
    }

    @PutMapping("/products/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO updatedProduct,
                                    @PathVariable long id) throws NotFoundException {
        this.productService.updateProduct(this.productMapper.toProduct(updatedProduct), id);
        return updatedProduct;
    }

    @PutMapping("/orders/{id}")
    public AdminOrderDTO updateOrderStats(@RequestBody AdminOrderDTO updatedOrder,
                                          @PathVariable long id) throws NotFoundException {
        this.adminService.updateOrder(this.orderMapper.toOrder(updatedOrder), id);
        return updatedOrder;
    }

    @PutMapping("/postal-offices/{id}")
    public PostalOfficeDTO updatePostalOffice(@RequestBody PostalOfficeDTO postalOfficeDTO,
                                              @PathVariable long id) throws NotFoundException {
        this.postalOfficeService.updatePostalOffice(this.postalOfficeMapper.toPostalOffice(postalOfficeDTO), id);
        return postalOfficeDTO;
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable long id) {
        this.productService.deleteProduct(id);
    }

    @DeleteMapping("/postal-offices/{id}")
    public void deletePostalOffice(@PathVariable long id) {
        this.postalOfficeService.deletePostalOffice(id);
    }

}
