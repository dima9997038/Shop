package com.example.shoes.repositories;

import com.example.shoes.models.Shoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoesRepository extends JpaRepository<Shoes, Long> {
    List<Shoes> findByCategory(String category);
}
