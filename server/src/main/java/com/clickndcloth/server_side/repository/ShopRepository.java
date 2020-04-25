package com.clickndcloth.server_side.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clickndcloth.server_side.models.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {

}
