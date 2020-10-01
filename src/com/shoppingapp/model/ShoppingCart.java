package com.shoppingapp.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart
{

	private List<Item> items = new ArrayList<Item>();
	private int cartId = 0;
	private int cartSize = 0;
	private int itemCountLength = 0;
	public List<Item> getItems()
	{
		return items;
	}
	public void setItems(List<Item> items)
	{
		this.items = items;
	}
	public int getCartId()
	{
		return cartId;
	}
	public void setCartId(int cartId)
	{
		this.cartId = cartId;
	}
	public int getItemCountLength()
	{
		for (Item item : items)
		{
			itemCountLength = (String.format("%d", item.getItemCount()).length() > itemCountLength) ? String.format("%d", item.getItemCount()).length() : itemCountLength;
		}
		return itemCountLength;
	}
	public void add(Item inventoryItem)
	{
		boolean addCondition = true;
		for (Item item : items)
		{
			if(item.getItemCode().equals(inventoryItem.getItemCode()))
			{
				addCondition = false;
				break;
			}
		
		}
		if(addCondition)
		{
			Item itemToAdd = new Item(inventoryItem.getItemName(), inventoryItem.getItemCode(), inventoryItem.getItemPrice());
			itemToAdd.setItemCount(0);
			items.add(itemToAdd);
		}
		cartSize++;
		for (Item item : items)
		{
			if(item.getItemCode().equals(inventoryItem.getItemCode()))
			{
				item.setItemCount(item.getItemCount()+1);
				System.out.println(item.getItemCount());
			}
		}
	}
	public int size()
	{
		return cartSize;
	}
	public double total()
	{
		double adder = 0;
		for (Item item : items)
		{
			adder += item.getItemPrice() * item.getItemCount();
		}
		
		return adder;
	}
}
