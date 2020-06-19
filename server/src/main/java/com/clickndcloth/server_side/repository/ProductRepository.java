package com.clickndcloth.server_side.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clickndcloth.server_side.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query(value = "SELECT product.* FROM Product AS product WHERE product.shop_id = ?1", nativeQuery = true)
	List<Product> findByShop(int shop_id);
	

}
