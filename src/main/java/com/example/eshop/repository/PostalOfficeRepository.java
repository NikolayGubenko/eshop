package com.example.eshop.repository;

import com.example.eshop.entity.PostalOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalOfficeRepository extends JpaRepository<PostalOffice, Long> {
}
