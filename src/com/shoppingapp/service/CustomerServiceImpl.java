package com.shoppingapp.service;

import com.shoppingapp.dao.UserDao;
import com.shoppingapp.dao.UserDaoImpl;
import com.shoppingapp.model.Customer;

public class CustomerServiceImpl implements CustomerService
{

	public UserDao userDao = new UserDaoImpl();
	
	@Override
	public Customer login(String userName, String userPass)
	{
		Customer cust = userDao.findByUserName(userName);
		if(userName.equalsIgnoreCase(cust.getUserName())
				&&userPass.equalsIgnoreCase(cust.getUserPass()))
		{
			return cust;
		}
		return null;
	}

	@Override
	public Customer createCustomer(Customer cust)
	{
		return userDao.create(cust);
	}

}
