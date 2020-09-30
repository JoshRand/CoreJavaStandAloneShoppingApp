package com.shoppingapp.service;

import java.util.List;

import com.shoppingapp.model.Customer;

public interface CustomerService
{
	public Customer login(String userName, String userPass);
	public Customer login(String userName, String userPass, List<Customer> custList);
	public Customer loginFileStreams(String userName, String userPass);
	public void createStreamCust(Customer cust);
	public void createCustomer(Customer cust);
}
