package com.shoppingapp.controller;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.ParseInfo;
import com.shoppingapp.model.Customer;
import com.shoppingapp.model.Invoice;
import com.shoppingapp.model.Item;
import com.shoppingapp.model.ShoppingCart;
import com.shoppingapp.service.CustomerService;
import com.shoppingapp.service.CustomerServiceImpl;
import com.shoppingapp.service.InventoryService;
import com.shoppingapp.service.InventoryServiceImpl;
import com.shoppingapp.service.InvoiceService;
import com.shoppingapp.service.InvoiceServiceImpl;
import com.shoppingapp.utility.ConsolePrinterUtility;
import com.shoppingapp.utility.FileStorageUtility;

public class ShoppingController
{
	public enum DataMode
	{
		COLLECTIONS,
		FILE_STREAMS,
		RELATIONAL_DATABASE
	}
	public DataMode dataMode = DataMode.FILE_STREAMS;
	
	public enum State
	{
		LOGGED_IN,
		LOGGED_OUT
	}
	
	public State state = State.LOGGED_OUT;

	public int option = 0;
	public boolean showCart = false;
	public boolean invoiceShow = false;
	public boolean showOrders = false;
	
	
	public ConsolePrinterUtility cpu = new ConsolePrinterUtility();
	public Customer cust = null;
	public ShoppingCart cart = new ShoppingCart();
	public Invoice invoice = null;
	public Scanner scan = null;
	public String opt = "";
	
	public CustomerService custService = new CustomerServiceImpl();
	public InventoryService inventoryService = new InventoryServiceImpl();
	public InvoiceService invoiceService = new InvoiceServiceImpl();
	
	public List<Item> inventory = new ArrayList<Item>();
	public List<Customer> custList = new ArrayList<Customer>();
	public List<Invoice> invoiceList = new ArrayList<Invoice>();
	{
		if(dataMode == DataMode.COLLECTIONS) 
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
		if(dataMode == DataMode.FILE_STREAMS)
		{
			inventory = inventoryService.getInventoryFromFile();
		}
		if(dataMode == DataMode.RELATIONAL_DATABASE)
		{
			inventory = inventoryService.getInventory();
			invoiceList = invoiceService.getInvoices();
		}
	}
	
	public void doShopping()
	{
		scan = new Scanner(System.in);
		
		while(true)
		{
			switch (state)
			{
				case LOGGED_OUT:
					cpu.mainMenu(dataMode.toString());
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
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + state);
			}
			try
			{
				
				opt = scan.nextLine();
				try
				{
					option = Integer.parseInt(opt);
				} catch (Exception e)
				{
					// TODO: handle exception
				}
				
				if(opt.equalsIgnoreCase("C"))
				{
					
					dataMode = DataMode.COLLECTIONS;
					inventory.clear();
					custList.clear();
					invoiceList.clear();
					inventory.add(new Item("Jerry", "Je1", 2000.20));
					inventory.add(new Item("G-Pro Wireless", "Gpw1", 150.30));
					inventory.add(new Item("Haiti", "Ha1", 69.50));
					inventory.add(new Item("Model D", "Mo1", 73.50));
					custList.add(new Customer("1","1"));
					custList.add(new Customer("2","2"));
					invoiceList.add(new Invoice("1", new ArrayList<Item>(), 202.20));
					continue;
				}
				else if(opt.equalsIgnoreCase("F"))
				{
					inventory = inventoryService.getInventoryFromFile();
					dataMode = DataMode.FILE_STREAMS;
					continue;
				}
				else if(opt.equalsIgnoreCase("R"))
				{
					inventory.clear();
					custList.clear();
					invoiceList.clear();
					inventory = inventoryService.getInventory();
					invoiceList = invoiceService.getInvoices();
					dataMode = DataMode.RELATIONAL_DATABASE;
					continue;
				}
				menuHandler(option);
			} 
			catch (InputMismatchException e)
			{
				cpu.invalidOption();
				scan.nextLine();
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
						logout();
						
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
						invoice = new Invoice(cust.getUserName(),cart.getItems(),cart.total());
						// check out
						if(dataMode == DataMode.COLLECTIONS)
						{
							
							invoiceList.add(invoice);
						}
						else if(dataMode == DataMode.FILE_STREAMS) {
							
						}
						else if(dataMode == DataMode.RELATIONAL_DATABASE)
						{
							invoiceService.saveInvoice(invoice);
							invoiceList = invoiceService.getInvoices();
						}
						
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
				switch(dataMode)
				{
					case COLLECTIONS:
						// Login with Collections
						cust = custService.login(uName, uPass, custList);
						break;
					case FILE_STREAMS:
						// Login with filestream/files
						cust = custService.loginFileStreams(uName,uPass);
						break;
					case RELATIONAL_DATABASE:
						// Login with MySQL UserDao
						cust = custService.login(uName, uPass);
						break;
					default:
						break;
				}
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
		String uPassCon = "";
		System.out.println("You can quit Account Creation by entering: Q");
		try
		{
			while(true)
			{
				System.out.println("Email:");
				uName = scan.nextLine();
				if(uName.equalsIgnoreCase("Q"))
					break;
				if(uName.matches("^\\S+@\\S+$"))
				{
					
					System.out.println("Password:");
					uPass = scan.nextLine();
					if(uPass.equalsIgnoreCase("Q"))
						break;
					if(passCheck(uPass))
					{
						System.out.println("Confirm Password:");
						uPassCon = scan.nextLine();
						if(uPassCon.equalsIgnoreCase("Q"))
							break;
						if(uPass.equals(uPassCon))
						{
							switch(dataMode)
							{
								case COLLECTIONS:
									// Create account with Collections
									custList.add(new Customer(uName, uPass));
									//System.out.println("cust lists:" + custList.toString());
									login(false);
									break;
								case FILE_STREAMS:
									// Create account with Filestream
									custService.createStreamCust(new Customer(uName, uPass));
									//System.out.println(fsu.custToString(new Customer(uName, uPass)));
									break;
								case RELATIONAL_DATABASE:
									// Create account with Dao
									custService.createCustomer(new Customer(uName, uPass));
									break;
								default:
									break;
							}
							break;
						}
					}
					else
					{
						System.out.println("password must be 8 characters long \nand at least 1 upper/ 1 lower/ 1 special/ 1 number.");
					}
				}
				else
				{
					System.out.println("Make sure Email is in ____@____.___ format");
				}
				
			}
			
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
			
			String opt = "";
			opt = scan.nextLine();
			try
			{
				option = Integer.parseInt(opt);
			} catch (Exception e)
			{
				// TODO: handle exception
			}
			
			
			System.out.println(opt);
			if(opt.equalsIgnoreCase("Q"))
			{
				invoiceShow = false;
			}
			else
			{
				for (Invoice invoice : invoiceList)
				{
					if( invoice.getInvNumber() == option && invoice.getUserName().equals(cust.getUserName()))
					{
						// Check to see if invoice isn't able to be changed (return period over)
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
					//System.out.println(invoiceList.toString());
					//System.out.println(invoiceFind.toString() + invoiceFind.getItems().toString());
					cpu.changeInvoice(invoiceFind);
					itemChoice = scan.nextLine();
					if(itemChoice.equalsIgnoreCase("Q"))
					{
						invoiceShow = false;
						break;
					}
					
					for (Item invItem : invoiceFind.getItems())
					{
						if(invItem.getItemCode().equalsIgnoreCase(itemChoice))
							itemToChange = invItem;
						
					}
					if(itemToChange == null)
					{
						System.out.println("Invalid Item Code");
					}
				}
				if(!itemChoice.equalsIgnoreCase("Q"))
				{
					cpu.returnOption();
					opt = scan.nextLine();
					option = Integer.parseInt(opt);
					if(opt.equalsIgnoreCase("Q"))
					{
						invoiceShow = false;
					}
					else
					{
						switch (option)
						{
							case 1:
								if(itemToChange.getItemCount() == 0)
								{
									
								}
								else
								{
									if(dataMode == DataMode.COLLECTIONS)
									{
										itemToChange.setItemCount(itemToChange.getItemCount()-1);
										double total = 0;
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
										
									}
									else if(dataMode == DataMode.FILE_STREAMS)
									{
										
										
									}
									else if(dataMode == DataMode.RELATIONAL_DATABASE)
									{
										invoice = invoiceFind;
										double total = 0;
										itemToChange.setItemCount(itemToChange.getItemCount()-1);
										for (Item item : invoiceFind.getItems())
										{
											total += item.getItemCount() * item.getItemPrice();
										}
										invoiceFind.setTotal(total);
										invoiceService.updateInvoice(invoiceFind.getInvNumber(),invoiceFind.getUserName(),invoice.getTotal(),itemToChange.getItemCode(),itemToChange.getItemCount());
									}
									invoiceShow = !invoiceShow;
								}
								
								break;
							case 2:
								invoiceShow = false;
								
								break;
							case 3:
								logout();
								break;
							default:
								break;
						
						}
					}
				}
				
				
			}
		} 
		catch (InputMismatchException e)
		{
		
			cpu.invalidOption();
			scan.nextLine();
			manageOrders();
		}
		
		
	}
	
	private boolean passCheck(String password)
	{
		int grade = 0;
		//length >= 8
		if(password.length() >= 8)
			grade++;
		// contains at least 1 digit
		if(password.matches("(?=.*[0-9]).*"))
			grade++;
		// contains at least 1 lower case
		if(password.matches("(?=.*[a-z]).*"))
			grade++;
		// contains at least 1 upper case
		if(password.matches("(?=.*[A-Z]).*"))
			grade++;
		// contains at least 1 special char
		if(password.matches("(?=.*[~!@#$%^&*()_-]).*"))
			grade++;
		if(grade == 5)
			return true;
		else
			return false;
	}
	public void logout()
	{
		cust = null;
		cart = new ShoppingCart();
		state = State.LOGGED_OUT;
	}
	public void exit()
	{
		scan.close();
		System.exit(0);
	}
		
	
}
