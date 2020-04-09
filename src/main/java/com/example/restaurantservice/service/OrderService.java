package com.example.restaurantservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.restaurantservice.exception.CartNotFoundException;
import com.example.restaurantservice.exception.OrderNotFoundException;
import com.example.restaurantservice.model.Cart;
import com.example.restaurantservice.model.OrderDto;
import com.example.restaurantservice.model.Orders;
import com.example.restaurantservice.repository.CartRepository;
import com.example.restaurantservice.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CartRepository cartRepository;

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

	public ResponseEntity<String> placeOrder(OrderDto orderDto) {
		Optional<Cart> cart = cartRepository.findById(orderDto.getCart().getCartId());
		logger.info("checking orderId present or not");
		if (cart.isPresent()) {
			Orders order = new Orders();
			order.setCart(orderDto.getCart());
			order.setPlacedDate(orderDto.getPlacedDate());
			order.setStatus("CREATED");
			
			orderRepository.save(order);
			logger.info("order placed");
			return new ResponseEntity<String>("order placed", HttpStatus.OK);

		} else {
			throw new CartNotFoundException("cartNotFoundException");
		}

	}

	public Optional<Orders> getOrderById(Long orderId) {

		return orderRepository.findById(orderId);
	}

	public List<Orders> getOrders() {
		return orderRepository.findAll();
	}
	
	public boolean cancelOrder(Long orderId) {
		Optional<Orders> order = orderRepository.findById(orderId);
		if(order.isPresent()) {
			order.get().setStatus("CANCELLED");
			orderRepository.save(order.get());
			return true;
		}else {
			return false;
		}
		
		
		
	}
}
