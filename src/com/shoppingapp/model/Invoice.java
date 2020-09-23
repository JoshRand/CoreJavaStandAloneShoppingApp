package com.shoppingapp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Invoice
{
	private static int invCount = 0;
	private int invNumber = 0;
	private double total;
	private int itemCountLength = 0;
	
	private String userName;
	private LocalDateTime creationDate = LocalDateTime.now();
	
	private List<Item> items = new ArrayList<Item>();

	public Invoice(String userName, List<Item> items, double total)
	{
		super();
		this.invNumber = ++invCount;
		this.userName = userName;
		this.items = items;
		this.total = total;
		
	}
	public int getInvNumber()
	{
		return invNumber;
	}
	public void setInvNumber(int invNumber)
	{
		this.invNumber = invNumber;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public LocalDateTime getCreationDate()
	{
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate)
	{
		this.creationDate = creationDate;
	}
	public List<Item> getItems()
	{
		return items;
	}
	public void setItems(List<Item> items)
	{
		this.items = items;
	}
	public double getTotal()
	{
		return total;
	}
	public void setTotal(double total)
	{
		this.total = total;
	}
	
	public int getItemCountLength()
	{
		for (Item item : items)
		{
			itemCountLength = (String.format("%d", item.getItemCount()).length() > itemCountLength) ? String.format("%d", item.getItemCount()).length() : itemCountLength;
		}
		return itemCountLength;
	}
	
	@Override
	public String toString()
	{
		return "Invoice [invNumber=" + invNumber + ", total=" + total + ", userName=" + userName + ", creationDate="
				+ creationDate + ", items=" + items + "]";
	}
	
}
