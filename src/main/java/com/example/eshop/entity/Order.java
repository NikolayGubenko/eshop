package com.example.eshop.entity;


import com.example.eshop.entity.enums.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderProduct> orderProducts;

    @Column(name = "date")
    private LocalDateTime orderDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.NEW;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "postal_id")
    private PostalOffice postalOffice;

    @Column(name = "description", length = 4000)
    private String description;

    @Column(name = "is_active")
    private boolean active;

}
