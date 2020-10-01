package com.shoppingapp.model;

public class Customer
{
	private String userName;
	private String userPass;
	private int invoiceCount = 0;
	
	public void setInvoiceCount(int invoiceCount)
	{
		this.invoiceCount = invoiceCount;
	}
	public int getInvoiceCount()
	{
		return invoiceCount;
	}
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
	public Customer()
	{
		super();
	}
	@Override
	public String toString()
	{
		return "Customer [userName=" + userName + ", userPass=" + userPass + "]";
	}
	
}
