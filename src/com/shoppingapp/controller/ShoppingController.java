package com.shoppingapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.shoppingapp.model.Customer;
import com.shoppingapp.model.Item;
import com.shoppingapp.utility.ConsolePrinterUtility;

public class ShoppingController
{
	public static List<Item> inventory = new ArrayList<Item>();
	public ConsolePrinterUtility cpu = new ConsolePrinterUtility();
	public Customer cust = null;
	
	static
	{
		// test inventory for items before sql
		inventory.add(new Item("Jerry", "Je1", 2000.20));
		inventory.add(new Item("G-Pro Wireless", "Gpw1", 150.30));
		inventory.add(new Item("Haiti", "Ha1", 69.50));
		inventory.add(new Item("Haiti", "Ha1", 69.50));
	}
	
	public enum State
	{
		LOGGED_IN,
		LOGGED_OUT,
		GUEST,
		CREATE_ACCOUNT,
		
	}
	
	public void doShopping()
	{
		//State state = State.LOGGED_OUT;
		State state = State.LOGGED_IN;
		while(true)
		{
			switch (state)
			{
				case LOGGED_OUT:
					cpu.mainMenu();
					break;
				case LOGGED_IN:
					cpu.shop(inventory);
					break;
					
				default:
					throw new IllegalArgumentException("Unexpected value: " + state);
			}
		}
		
	}
		
	
}
