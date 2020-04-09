package com.example.restaurantservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurantservice.model.Orders;

public interface OrderRepository extends JpaRepository<Orders,Long>{

}
