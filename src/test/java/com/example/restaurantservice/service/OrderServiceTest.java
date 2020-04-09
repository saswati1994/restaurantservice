package com.example.restaurantservice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.restaurantservice.model.Cart;
import com.example.restaurantservice.model.Item;
import com.example.restaurantservice.model.OrderDto;
import com.example.restaurantservice.model.Orders;
import com.example.restaurantservice.repository.OrderRepository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderServiceTest {

	@InjectMocks
	OrderService orderService;

	@Mock
	OrderRepository orderRepository;

	@Test
	public void placeOrder() {

		Item item = new Item();
		item.setItemDesc("veg pizza with onion capsicum toppings");
		item.setItemId(1L);
		item.setItemName("veg pizza");
		item.setPrice(250L);

		List<Item> itemList = new ArrayList<Item>();
		itemList.add(item);

		Cart cart = new Cart();
		cart.setCartId(1L);
		cart.setItems(itemList);
		cart.setUserId("100");

		Orders order = new Orders();
		order.setCart(cart);
		order.setOrderId(1L);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		dateFormat.format(date);
		order.setPlacedDate(date);
		order.setStatus("CREATED");

		OrderDto orderRequest = new OrderDto();
		orderRequest.setCart(cart);
		orderRequest.setPlacedDate(date);

		when(orderRepository.save(any(Orders.class))).thenReturn(order);
		ResponseEntity<String> orderResponse = orderService.placeOrder(orderRequest);
		assertNotNull(orderResponse);
	}

	@Test
	public void getOrderById() {
		Orders order = new Orders();
		when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
		java.util.Optional<Orders> optionalOrder = orderService.getOrderById(1L);
		assertNotNull(optionalOrder);
	}
	
	@Test
	public void getOrderByIdNegative() {
	
	}

	@Test
	public void getOrders() {
		List<Orders> orderList = new ArrayList<>();
		when(orderRepository.findAll()).thenReturn(orderList);
		orderList = orderService.getOrders();
	}
	
	@Test
	public void getOrdersNegative() {
		
	}

	@Test
	public void cancelOrder() {

		Item item = new Item();
		item.setItemDesc("veg pizza with onion capsicum toppings");
		item.setItemId(1L);
		item.setItemName("veg pizza");
		item.setPrice(250L);

		List<Item> itemList = new ArrayList<Item>();
		itemList.add(item);

		Cart cart = new Cart();
		cart.setCartId(1L);
		cart.setItems(itemList);
		cart.setUserId("100");

		Orders order = new Orders();
		order.setCart(cart);
		order.setOrderId(1L);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		dateFormat.format(date);
		order.setPlacedDate(date);
		order.setStatus("CREATED");

		OrderDto orderRequest = new OrderDto();
		orderRequest.setCart(cart);
		orderRequest.setPlacedDate(date);

		when(orderRepository.save(any(Orders.class))).thenReturn(order);

		boolean result = false;
		result = orderService.cancelOrder(1L);
		assertEquals(result, true);

	}
	@Test
	public void cancelOrderNegative() {
	Item item = new Item();
	item.setItemDesc("veg pizza with onion capsicum toppings");
	item.setItemId(1L);
	item.setItemName("veg pizza");
	item.setPrice(250L);

	List<Item> itemList = new ArrayList<Item>();
	itemList.add(item);

	Cart cart = new Cart();
	cart.setCartId(1L);
	cart.setItems(itemList);
	cart.setUserId("100");

	Orders order = new Orders();
	order.setCart(cart);
	order.setOrderId(1L);
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	dateFormat.format(date);
	order.setPlacedDate(date);
	order.setStatus("CREATED");

	OrderDto orderRequest = new OrderDto();
	orderRequest.setCart(cart);
	orderRequest.setPlacedDate(date);

	when(orderRepository.save(any(Orders.class))).thenReturn(order);

	boolean result = false;
	result = orderService.cancelOrder(2L);
	assertEquals(result, false);
	}

}
