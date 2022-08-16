package com.eidiko.niranjana.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {

	private String OrderId;
	private String name;
	private int qty;
	private double price; 
}
