package com.example.restaurantservice.exception;

public class CartNotFoundException extends RuntimeException{
	
	public CartNotFoundException() {
		super();
	}
	
	public CartNotFoundException(String msg) {
		super(msg);
	}

}
