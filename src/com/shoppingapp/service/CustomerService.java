package com.shoppingapp.service;

import java.util.List;

import com.shoppingapp.model.Customer;

public interface CustomerService
{
	public Customer login(String userName, String userPass);
	public Customer login(String userName, String userPass, List<Customer> custList);
	public Customer createCustomer(Customer cust);
}
