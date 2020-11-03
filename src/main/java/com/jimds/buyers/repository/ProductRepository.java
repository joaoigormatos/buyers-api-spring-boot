package com.jimds.buyers.repository;

import com.jimds.buyers.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findUserByTitle(String title);

    List<Product> findAllByBrand(String brand);

    List<Product> findAllByTag(String tag);
}
