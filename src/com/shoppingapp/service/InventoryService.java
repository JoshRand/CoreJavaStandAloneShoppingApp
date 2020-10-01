package com.shoppingapp.service;

import java.util.List;

import com.shoppingapp.model.Item;

public interface InventoryService
{
	public List<Item> getInventory();
	public List<Item> getInventoryFromFile();
}
