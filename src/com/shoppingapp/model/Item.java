package com.shoppingapp.model;

public class Item
{
	private String itemName;
	private String itemCode;
	private double itemPrice = 0;
	private int itemCount = 0;
	
	public int getItemCount()
	{
		return itemCount;
	}
	public void setItemCount(int itemCount)
	{
		this.itemCount = itemCount;
	}
	public String getItemName()
	{
		return itemName;
	}
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	public String getItemCode()
	{
		return itemCode;
	}
	public void setItemCode(String itemCode)
	{
		this.itemCode = itemCode;
	}
	public double getItemPrice()
	{
		return itemPrice;
	}
	public void setItemPrice(double itemPrice)
	{
		this.itemPrice = itemPrice;
	}
	public Item(String itemName, String itemCode, double itemPrice)
	{
		super();
		this.itemName = itemName;
		this.itemCode = itemCode;
		this.itemPrice = itemPrice;
	}
	
	public Item()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	public Item(String itemName, String itemCode, double itemPrice, int itemCount)
	{
		super();
		this.itemName = itemName;
		this.itemCode = itemCode;
		this.itemPrice = itemPrice;
		this.itemCount = itemCount;
	}
	@Override
	public String toString()
	{
		return "Item [itemName=" + itemName + ", itemCode=" + itemCode + ", itemPrice=" + itemPrice + ", itemCount="
				+ itemCount + "]";
	}
	
	
}
