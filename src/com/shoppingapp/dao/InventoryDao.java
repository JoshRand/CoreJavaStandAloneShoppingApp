package com.shoppingapp.dao;

import java.util.List;

import com.shoppingapp.model.Customer;
import com.shoppingapp.model.Item;

public interface InventoryDao
{
	
	public void create(Item item);
	public void update(Item item);
	public void deleteByItemName();
	public List<Item> getInventory();
}
