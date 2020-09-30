package com.shoppingapp.service;

import java.util.List;

import com.shoppingapp.dao.InventoryDao;
import com.shoppingapp.dao.InventoryDaoImpl;
import com.shoppingapp.model.Item;
import com.shoppingapp.utility.FileStorageUtility;

public class InventoryServiceImpl implements InventoryService
{
	public FileStorageUtility fsu = new FileStorageUtility();
	
	InventoryDao inventoryDao = new InventoryDaoImpl();
	
	@Override
	public List<Item> getInventory()
	{
		return inventoryDao.getInventory();
	}

	@Override
	public List<Item> getInventoryFromFile()
	{
		
		return fsu.getInventory();
	}

}
