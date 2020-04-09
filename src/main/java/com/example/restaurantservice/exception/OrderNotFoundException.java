package com.example.restaurantservice.exception;

public class OrderNotFoundException extends RuntimeException {

	public OrderNotFoundException() {

		super();
	}

	public OrderNotFoundException(String str) {
		super(str);
	}
}
