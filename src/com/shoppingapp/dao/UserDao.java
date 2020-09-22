package com.shoppingapp.dao;

import com.shoppingapp.model.Customer;

public interface UserDao
{
	
	public Customer create(Customer cust);
	public void update(Customer cust);
	public void deleteByName();
	public Customer findByUserName(String userName);
}
