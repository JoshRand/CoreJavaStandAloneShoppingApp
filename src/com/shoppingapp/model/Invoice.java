package com.shoppingapp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Invoice
{
	private int invNumber;
	private String userName;
	private LocalDateTime ldt = LocalDateTime.now();
	private List<Item> items = new ArrayList<Item>();
}
