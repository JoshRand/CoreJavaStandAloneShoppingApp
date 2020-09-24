package com.shoppingapp.controller;

import java.io.Console;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
	public List<Invoice> invoiceList = new ArrayList<Invoice>();
	{
		// Collections Inventory and Users before File streams and MySQL
		inventory.add(new Item("Jerry", "Je1", 2000.20));
		inventory.add(new Item("G-Pro Wireless", "Gpw1", 150.30));
		inventory.add(new Item("Haiti", "Ha1", 69.50));
		inventory.add(new Item("Model D", "Mo1", 73.50));
		custList.add(new Customer("1","1"));
		custList.add(new Customer("2","2"));
		invoiceList.add(new Invoice("1", new ArrayList<Item>(), 202.20));

	}
	
	
	public ConsolePrinterUtility cpu = new ConsolePrinterUtility();
	public Customer cust = null;
	public ShoppingCart cart = new ShoppingCart();
	public Invoice invoice = null;
	public Scanner scan = null;
	
	public CustomerService custService = new CustomerServiceImpl();
	
	public int option = 0;
	public boolean showCart = false;
	public boolean invoiceShow = false;
	public boolean showOrders = false;
	
	public enum State
	{
		LOGGED_IN,
		LOGGED_OUT
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
					} 
					catch (InputMismatchException e)
					{
						cpu.invalidOption();
						scan.nextLine();
					}
					
					break;
				case LOGGED_IN:
					
					if(invoiceShow) {
						cpu.showInvoice(invoice);
					}
					else if(showCart)
					{
						cpu.cart(cart);
					}
					else
					{
						cpu.shop(inventory, cart);
						
					}
					try
					{
						option = scan.nextInt();
						scan.nextLine();
						menuHandler(option);
					} 
					catch (Exception e)
					{
						cpu.invalidOption();
						scan.nextLine();
						
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
					login(false);
					break;
				case 3:
					login(true);
					break;
				case 4:
					exit();
					break;
			}
		else if(state == State.LOGGED_IN) 
		{
			if(invoiceShow)
			{
				switch (option)
				{
					case 1:
						invoiceShow = !invoiceShow;
						for (Item item : inventory)
						{
							item.setItemCount(0);
						}
						break;
					case 2:
						cust = null;
						invoice = null;
						cart = new ShoppingCart();
						for (Item item : inventory)
						{
							item.setItemCount(0);
						}
						state = State.LOGGED_OUT;
						invoiceShow = !invoiceShow;
						break;
					
				}
			}
			else if(!showCart)
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
					case 7:
						manageOrders();
						break;
				}
			}
			
			else
			{
				switch (option)
				{
					case 1:
						// check out
						invoice = new Invoice(cust.getUserName(),cart.getItems(),cart.total());
						invoiceList.add(invoice);
						cart = new ShoppingCart();
						showCart = !showCart;
						invoiceShow = !invoiceShow;
						break;
					case 2:
						showCart = !showCart;
						break;
					
				}
			}
		}	
		
	}
	
	public void login(boolean guest)
	{
		if(!guest)
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
		else
		{
			cust = new Customer("Guest", "Guest");
			state = (cust == null) ? State.LOGGED_OUT : State.LOGGED_IN;
		}
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
			login(false);
			// Create account with Filestream
			
			// Create account with Dao
			
		} catch (Exception e)
		{
			System.out.println("Error Creating Account");
		}
		
		
	}
	
	public void manageOrders()
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		Invoice invoiceFind = null;
		String itemChoice = "";
		Item itemToChange = null;
		scan = new Scanner(System.in);
		List<Invoice> foundOrders = new ArrayList<Invoice>();
		for (Invoice invoice : invoiceList)
		{
			if(cust.getUserName().equalsIgnoreCase(invoice.getUserName()))
			{
				foundOrders.add(invoice);
			}
			
		}
		cpu.orderHistory(foundOrders);
		try
		{
			option = scan.nextInt();
			scan.nextLine();
			for (Invoice invoice : invoiceList)
			{
				if(invoice.getInvNumber() == option )
				{
					Duration durInv = Duration.between(ldt, invoice.getCreationDate());
					Duration durDif = Duration.ofDays(15);
					if(durInv.compareTo(durDif) > 0)
					{
						System.out.println("Return period is over");
					}
					else
					{
						invoiceFind = invoice;
					}
					
				}
			}
			if(invoiceFind == null)
			{
				throw new InputMismatchException();
			}
			while(itemToChange == null) 
			{
				cpu.changeInvoice(invoiceFind);
				itemChoice = scan.nextLine();
				for (Item invItem : invoice.getItems())
				{
					if(invItem.getItemCode().equalsIgnoreCase(itemChoice))
						itemToChange = invItem;
					
				}
				if(itemToChange == null)
				{
					System.out.println("Invalid Item Code");
				}
			}
			cpu.returnOption();
			option = scan.nextInt();
			scan.nextLine();
			switch (option)
			{
				case 1:
					if(itemToChange.getItemCount() == 0)
					{
						
					}
					else
					{
						itemToChange.setItemCount(itemToChange.getItemCount()-1);
						int total = 0;
						for (Item invItem : invoice.getItems())
						{
							if(itemToChange.getItemCount() == 0)
							{
								invItem = null;
							}
							else if(invItem.getItemCode().equalsIgnoreCase(itemChoice))
							{
								invItem = itemToChange;
							}
							
						}
						for (Item item : invoice.getItems())
						{
							total += item.getItemCount() * item.getItemPrice();
						}
						invoice.setTotal(total);
						
						invoiceShow = !invoiceShow;
					}
					
					break;
				default:
					break;
			
			}
			
			
		} 
		catch (InputMismatchException e)
		{
			cpu.invalidOption();
			scan.nextLine();
			manageOrders();
		}
		
		
	}
	
	public void exit()
	{
		scan.close();
		System.exit(0);
	}
		
	
}
