package com.shoppingapp.utility;

import java.util.List;

import com.shoppingapp.model.Customer;
import com.shoppingapp.model.Item;
import com.shoppingapp.utility.ColorsUtility;

public class ConsolePrinterUtility
{
	Item item = null;
	ColorsUtility cu = new ColorsUtility();
	public void mainMenu()
	{
		System.out.println(cu.BLUE_BRIGHT + "+----------------------------+");
		System.out.println("|  ShoppingApp Welcomes You! |");
		System.out.println("+----------------------------+" + cu.RESET);
		System.out.println("1. Create New Account");
		System.out.println("2. Login");
		System.out.println("3. Exit.");
		System.out.println();
		enterChoice(3);
		//System.out.println("Enter Choice (1,2, or 3) :");
		
	}
	public void login()
	{
		
		System.out.println(cu.BLUE_BRIGHT + "+----------------------+");
		System.out.println("|  Enter Login Details |");
		System.out.println("+----------------------+" + cu.RESET);
		
	}
	public void shop(List<Item> inventory)
	{
		int signOutNumber = 0;
		int itemNameLength = 0;
		int itemCodeLength = 0;
		for (Item item : inventory)
		{
			if(item.getItemName().length() >= itemNameLength) 
				itemNameLength = item.getItemName().length();
			if(item.getItemCode().length() >= itemCodeLength)
				itemCodeLength = item.getItemCode().length();
		}
		System.out.println(cu.BLUE_BRIGHT + "+----------------------+");
		System.out.println("|  WELCOME Customer!!! |");
		System.out.println("+----------------------+" + cu.RESET);
		System.out.print("    Name"+ spaces(itemNameLength-4));
		System.out.print("Code"+spaces(itemCodeLength-4));
		System.out.print("Price");
		System.out.println();
		for (int i = 0; i < inventory.size(); ++i)
		{
			item = inventory.get(i);
			System.out.println(""+(i+1)+".  "+item.getItemName() +spaces(itemNameLength-item.getItemName().length())+ item.getItemCode() 
			+ spaces(itemCodeLength-item.getItemCode().length()) + "$" +String.format("%.2f",item.getItemPrice()));
			signOutNumber = i+2;
		}
		System.out.println(""+signOutNumber+".  Sign Out");
		System.out.println();
		enterChoice(signOutNumber);
		//System.out.println("Enter Choice (1,2,3,4,5 or 6) :");
		
	}
	public String spaces(int i)
	{
		String space = " ";
		
		space = space.repeat(i + 2);
		
		return space;
	}
	public void recentTrans()
	{
		System.out.println(cu.BLUE_BRIGHT + "+------------------------+");
		System.out.println("| 5 Recent Transactions: |");
		System.out.println("+------------------------+" + cu.RESET);
	}
	public void invalidCreds()
	{
		System.out.println(cu.RED_BRIGHT +  "Invalid Credentials. Try Again!"  + cu.RESET);
	}
	public void invalidOption()
	{
		System.out.println(cu.RED_BRIGHT + "Invalid Option, please choose from the list..." + cu.RESET);
	}
	public void enterChoice(int num)
	{
		System.out.print(cu.GREEN_BRIGHT + "Enter Choice (");
		for(int i = 0; i <= num - 2 ; i++)
		{
			if(i <= num - 3) 
			{
				System.out.printf("%d,",i + 1);
			}
			else
			{
				System.out.printf("%d or %d", i+1, i+2);
			}
		}
		
		System.out.print(") :" + cu.RESET);
		System.out.println();
	}
	public void displayCustomerInfo(Customer cust)
	{
		System.out.println(cu.YELLOW_BRIGHT + "+----------------------+");
		System.out.println("|   Customer Details   |");
		System.out.println("+----------------------+" );
		System.out.println("+-----  CUSTOMER  -----+");
		System.out.println(" U NAME: " + cust.getUserName());
		System.out.println(" U PASS: " + cust.getUserPass() + cu.RESET);
		
		
		System.out.println();
		//System.out.println("Enter Choice (1,2,3,4,5 or 6) :");
		
	}
}

