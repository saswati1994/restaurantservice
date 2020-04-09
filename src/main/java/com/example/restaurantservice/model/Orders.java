package com.example.restaurantservice.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name= "orders")
public class Orders {
	
	@Id
	 // @SequenceGenerator(sequenceName = "cart_seq", name = "cart_seq_gen", allocationSize = 1)
	  @GeneratedValue(strategy = GenerationType.IDENTITY/*, generator = "cart_seq_gen"*/)
	  @Column(name = "order_id")
	  private Long orderId;
	
	@OneToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	  @JoinColumn(name = "order_cartId")
	  private Cart cart;

	  @Column(name = "date")
	  private Date placedDate;
	  
	  @Column(name="status")
      private String status;

	
	  
	  
}
