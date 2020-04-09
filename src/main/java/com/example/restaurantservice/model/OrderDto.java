package com.example.restaurantservice.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

	private Date placedDate;
	private Cart cart;

	public OrderDto(Date placedDate, Cart cart) {
		super();
		this.placedDate = placedDate;
		this.cart = cart;
	}

}
