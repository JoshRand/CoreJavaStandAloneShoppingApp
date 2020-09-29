package com.shoppingapp.service;

import java.util.List;

import com.shoppingapp.dao.InventoryDao;
import com.shoppingapp.dao.InventoryDaoImpl;
import com.shoppingapp.model.Item;

public class InventoryServiceImpl implements InventoryService
{
	InventoryDao inventoryDao = new InventoryDaoImpl();
	
	@Override
	public List<Item> getInventory()
	{
		return inventoryDao.getInventory();
	}

}
