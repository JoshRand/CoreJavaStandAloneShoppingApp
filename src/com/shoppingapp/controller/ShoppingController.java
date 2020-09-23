package com.shoppingapp.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.shoppingapp.model.Customer;
import com.shoppingapp.model.Invoice;
import com.shoppingapp.model.Item;
import com.shoppingapp.model.ShoppingCart;
import com.shoppingapp.service.CustomerService;
import com.shoppingapp.service.CustomerServiceImpl;
import com.shoppingapp.utility.ConsolePrinterUtility;

public class ShoppingController
{
	
	public List<Item> inventory = new ArrayList<Item>();
	public List<Customer> custList = new ArrayList<Customer>();
	public List<Invoice> invList = new ArrayList<Invoice>();
	{
		// Collections Inventory and Users before File streams and MySQL
		inventory.add(new Item("Jerry", "Je1", 2000.20));
		inventory.add(new Item("G-Pro Wireless", "Gpw1", 150.30));
		inventory.add(new Item("Haiti", "Ha1", 69.50));
		inventory.add(new Item("Model D", "Mo1", 73.50));
		custList.add(new Customer("1","1"));
		custList.add(new Customer("2","2"));

	}
	
	
	public ConsolePrinterUtility cpu = new ConsolePrinterUtility();
	public Customer cust = null;
	public ShoppingCart cart = new ShoppingCart();
	public Scanner scan = null;
	
	public CustomerService custService = new CustomerServiceImpl();
	
	public int option = 0;
	public boolean showCart = false;
	public enum State
	{
		LOGGED_IN,
		LOGGED_OUT,
		GUEST,
		CREATE_ACCOUNT,
		
	}
	public State state = State.LOGGED_OUT;//State state = State.LOGGED_IN;
	
	
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
					if(showCart)
					{
						cpu.cart(cart);
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
					}
					else
					{
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
					}
					
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
		{
			if(!showCart)
			{
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
					case 6:
						showCart = !showCart;
						break;
				}
			}
			else
			{
				switch (option)
				{
					case 1:
						// check out
						
						break;
					case 2:
						showCart = !showCart;
						break;
					
				}
			}
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
				case 6:
					showCart = !showCart;
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
			System.out.println("Email:");
			uName = scan.nextLine();
			System.out.println("Password:");
			uPass = scan.nextLine();
			System.out.println();
			// Login with Collections
			cust = custService.login(uName, uPass, custList);
			// Login with filestream/files
			//
			// Login with MySQL UserDao
			//cust = custService.login(uName, uPass);
		} catch (Exception e)
		{
			cpu.invalidCreds();
		}
		state = (cust == null) ? State.LOGGED_OUT : State.LOGGED_IN;
		
	}
	
	public void createAccount()
	{
		String uName = "";
		String uPass = "";
		try
		{
			System.out.println("Email:");
			uName = scan.nextLine();
			System.out.println("Password:");
			uPass = scan.nextLine();
			// Create account with Collections
			custList.add(new Customer(uName, uPass));
			System.out.println("cust lists:" + custList.toString());
			login();
			// Create account with Filestream
			
			// Create account with Dao
			
		} catch (Exception e)
		{
			System.out.println("Error Creating Account");
		}
		
		
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
