package com.shoppingapp.model;

public class Customer
{
	private String userName;
	private String userPass;
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getUserPass()
	{
		return userPass;
	}
	public void setUserPass(String userPass)
	{
		this.userPass = userPass;
	}
	public Customer(String userName, String userPass)
	{
		super();
		this.userName = userName;
		this.userPass = userPass;
	}
	@Override
	public String toString()
	{
		return "Customer [userName=" + userName + ", userPass=" + userPass + "]";
	}
	
}
