package com.shoppingapp.service;

import java.util.List;

import com.shoppingapp.dao.UserDao;
import com.shoppingapp.dao.UserDaoImpl;
import com.shoppingapp.model.Customer;
import com.shoppingapp.utility.FileStorageUtility;

public class CustomerServiceImpl implements CustomerService
{
	public FileStorageUtility fsu = new FileStorageUtility();

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
	public void createCustomer(Customer cust)
	{
		userDao.create(cust);
	}

	@Override
	public Customer login(String userName, String userPass, List<Customer> custList)
	{
		for (Customer cust : custList)
		{
			if(userName.equalsIgnoreCase(cust.getUserName())
					&&userPass.equalsIgnoreCase(cust.getUserPass()))
			{
				return cust;
			}
		}
		
		return null;
	}

	@Override
	public Customer loginFileStreams(String userName, String userPass)
	{
		Customer cust = fsu.getCustomer(userName);
		if(userName.equalsIgnoreCase(cust.getUserName())
				&&userPass.equalsIgnoreCase(cust.getUserPass()))
		{
			return cust;
		}
		return null;
		
	}
	@Override
	public void createStreamCust(Customer cust)
	{
		fsu.saveCustToFile(cust);
		
	}

	@Override
	public List<Customer> getAllCustomersFromFileStream()
	{
		
		return fsu.getCustomers();
	}

	@Override
	public void updateAllCustomers(List<Customer> custs)
	{
		fsu.updateAllCustomersToFile(custs);
		
	}

}
