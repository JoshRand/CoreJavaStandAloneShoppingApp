package com.shoppingapp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Invoice
{

	interface ItemInterface
	{
		public List<Item> copyItems(List<Item> items);
	}

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
		
		ItemInterface f = ((itemList) -> {
			List<Item> list = itemList;
			List<Item> listCopied = new ArrayList<Item>();
			list.forEach((temp)->{
				listCopied.add(new Item(temp.getItemName(), temp.getItemCode(), temp.getItemPrice(), temp.getItemCount()));
			});
			
			return listCopied;
		});
		
		this.items = f.copyItems(items);
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
