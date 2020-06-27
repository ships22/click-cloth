package com.clickndcloth.server_side.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clickndcloth.server_side.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query(value = "SELECT product.* FROM Product AS product WHERE product.shop_id = ?1", nativeQuery = true)
	List<Product> findByShop(int shop_id);
	
	//List<Product> findByIdIn(List<Integer> ids);
	
    @Query(nativeQuery = true, value = "SELECT * FROM Product as p WHERE p.id IN (:ids)")  
    List<Product> findAllByProductId(@Param("ids") List<Integer> ids);
    
    
    @Query(value = "SELECT * FROM Product AS product WHERE product.product_ref LIKE 'sf%'", nativeQuery = true)
    List<Product> findAllByProductLadies();
	
    @Query(value = "SELECT * FROM Product AS product WHERE product.product_ref LIKE 'sh%'", nativeQuery = true)
    List<Product> findAllByProductGents();
	
    @Query(value = "SELECT * FROM Product AS product WHERE product.product_ref LIKE 'se%'", nativeQuery = true)
    List<Product> findAllByProductChildren();
}
