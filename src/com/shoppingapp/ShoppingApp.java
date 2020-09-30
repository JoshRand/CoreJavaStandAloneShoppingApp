package com.shoppingapp;

import java.util.ArrayList;
import java.util.List;

import com.shoppingapp.controller.ShoppingController;
import com.shoppingapp.model.Customer;
import com.shoppingapp.model.Item;
import com.shoppingapp.utility.ConsolePrinterUtility;

public class ShoppingApp
{
	
	
	public static void main(String[] args)
	{
		ShoppingController shoppingController = new ShoppingController();
		shoppingController.doShopping();
	
	}
	
}
