package com.shoppingapp.service;

import com.shoppingapp.model.Customer;

public interface CustomerService
{
	public Customer login(String userName, String userPass);
	public Customer createCustomer(Customer cust);
}
