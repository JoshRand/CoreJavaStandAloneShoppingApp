package com.shoppingapp.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.shoppingapp.model.Customer;
import com.shoppingapp.model.Item;
import com.shoppingapp.model.ShoppingCart;
import com.shoppingapp.service.CustomerService;
import com.shoppingapp.service.CustomerServiceImpl;
import com.shoppingapp.utility.ConsolePrinterUtility;

public class ShoppingController
{
	public List<Item> inventory = new ArrayList<Item>();
	
	public ConsolePrinterUtility cpu = new ConsolePrinterUtility();
	public Customer cust = null;
	public ShoppingCart cart = new ShoppingCart();
	public Scanner scan = null;
	
	public int option = 0;
	public State state = State.LOGGED_OUT;//State state = State.LOGGED_IN;
	public CustomerService custService = new CustomerServiceImpl();
	
	{
		// test inventory for items before sql
		inventory.add(new Item("Jerry", "Je1", 2000.20));
		inventory.add(new Item("G-Pro Wireless", "Gpw1", 150.30));
		inventory.add(new Item("Haiti", "Ha1", 69.50));
		inventory.add(new Item("Model D", "Mo1", 73.50));
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
		scan = new Scanner(System.in);
		
		while(true)
		{
			switch (state)
			{
				case LOGGED_OUT:
					cpu.mainMenu();
					try
					{
						option = scan.nextInt();
						scan.nextLine();
						menuHandler(option);
					} catch (Exception e)
					{
						cpu.invalidOption();
					}
					
					break;
				case LOGGED_IN:
					cpu.shop(inventory, cart);
					try
					{
						option = scan.nextInt();
						scan.nextLine();
						menuHandler(option);
					} catch (Exception e)
					{
						cpu.invalidOption();
					}
					break;
				case GUEST:
					cpu.shop(inventory, cart);
					try
					{
						option = scan.nextInt();
						scan.nextLine();
						menuHandler(option);
					} catch (Exception e)
					{
						cpu.invalidOption();
					}
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + state);
			}
		}
		
	}
	
	public void menuHandler(int option)
	{
		if(state == State.LOGGED_OUT)
			switch (option)
			{
				case 1:
					createAccount();
					break;
				case 2:
					login();
					break;
				case 3:
					guest();
					break;
				case 4:
					exit();
					break;
			}
		else if(state == State.LOGGED_IN)
			switch (option)
			{
				case 1:
					cart.add(inventory.get(option - 1));
					break;
				case 2:
					cart.add(inventory.get(option - 1));
					break;
				case 3:
					cart.add(inventory.get(option - 1));
					break;
				case 4:
					cart.add(inventory.get(option - 1));
					
					for (Item item : inventory)
					{
						System.out.println(item.getItemCount());
					}
					break;
				case 5:
					cust = null;
					cart = new ShoppingCart();
					state = State.LOGGED_OUT;
					break;
			}
		else if(state == State.GUEST)
			switch (option)
			{
				case 1:
					cart.add(inventory.get(option - 1));
					break;
				case 2:
					cart.add(inventory.get(option - 1));
					break;
				case 3:
					cart.add(inventory.get(option - 1));
					break;
				case 4:
					cart.add(inventory.get(option - 1));
					
					for (Item item : inventory)
					{
						System.out.println(item.getItemCount());
					}
					break;
				case 5:
					cust = null;
					cart = new ShoppingCart();
					state = State.LOGGED_OUT;
					break;
			}
	}
	
	public void login()
	{
		cpu.login();
		String uName = "";
		String uPass = "";
		try
		{
			uName = scan.nextLine();
			uPass = scan.nextLine();
			System.out.println();
			cust = custService.login(uName, uPass);
		} catch (Exception e)
		{
			cpu.invalidCreds();
		}
		state = (cust == null) ? State.LOGGED_OUT : State.LOGGED_IN;
		
	}
	
	public void createAccount()
	{
		
	}
	
	public void guest()
	{
		state = State.GUEST;
	}
	
	public void exit()
	{
		System.exit(0);
	}
		
	
}
