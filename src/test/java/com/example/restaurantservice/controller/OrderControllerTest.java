package com.example.restaurantservice.controller;

import com.example.restaurantservice.repository.CartRepository;
import com.example.restaurantservice.repository.ItemRepository;
import com.example.restaurantservice.repository.OrderRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.restaurantservice.model.Cart;
import com.example.restaurantservice.model.Item;
import com.example.restaurantservice.model.OrderDto;
import com.example.restaurantservice.model.Orders;
import com.example.restaurantservice.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderController.class)
@SpringBootTest
public class OrderControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private OrderService orderService;

	@MockBean
	private CartRepository cartRepository;

	@MockBean
	private ItemRepository itemRepository;

	@MockBean
	private OrderRepository orderRepository;
	
	@Test
	public void placeOrder() throws Exception {
		Item item = new Item();
		item.setItemDesc("veg pizza with onion capsicum toppings");
		item.setItemId(1L);
		item.setItemName("veg pizza");
		item.setPrice(250L);
		List<Item> itemList= new ArrayList();
		itemList.add(item);

		Cart cart = new Cart();
		cart.setCartId(1L);
		cart.setItems(itemList);
		cart.setUserId("100");

		OrderDto orderDto = new OrderDto();
		orderDto.setCart(cart);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		dateFormat.format(date);
		orderDto.setPlacedDate(date);

		when(orderService.placeOrder(any(OrderDto.class))).thenReturn(new ResponseEntity<>("order placed", HttpStatus.OK));
		RequestBuilder request = MockMvcRequestBuilders.post("/order")
				.content(objectMapper.writeValueAsString((orderDto)))
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();
	}
	@Test
	public void getOrderById() throws Exception{

		Orders order = new Orders();
		when(orderService.getOrderById(1L)).thenReturn(Optional.of(order));
		RequestBuilder request = MockMvcRequestBuilders.get("/order/1");
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();
	}
	
	@Test
	public void getOrders() throws Exception{
		
		List<Orders> orderList = new ArrayList<Orders>();
		Orders order = new Orders();
		orderList.add(order);
		when(orderService.getOrders()).thenReturn(orderList);
		RequestBuilder request = MockMvcRequestBuilders.get("/orders");
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();
	}
	
	@Test
    public void cancelOrder() throws Exception{
		
		when(orderService.cancelOrder(anyLong())).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.put("/order/cancel/1");
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();
	}
}