package com.shoppingapp.utility;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.shoppingapp.model.Customer;
import com.shoppingapp.model.Invoice;
import com.shoppingapp.model.Item;
import com.shoppingapp.model.ShoppingCart;
import com.shoppingapp.utility.ColorsUtility;

public class ConsolePrinterUtility
{
	Item item = null;
	ColorsUtility cu = new ColorsUtility();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a MM/dd/yyyy");
	public void mainMenu()
	{
		System.out.println(cu.BLUE_BRIGHT + "+----------------------------+");
		System.out.println("|  ShoppingApp Welcomes You! |");
		System.out.println("+----------------------------+" + cu.RESET);
		System.out.println("1. Create New Account");
		System.out.println("2. Login");
		System.out.println("3. Guest");
		System.out.println("4. Exit.");
		System.out.println();
		enterChoice(4, true);
		
	}
	public void login()
	{
		
		System.out.println(cu.BLUE_BRIGHT + "+----------------------+");
		System.out.println("|  Enter Login Details |");
		System.out.println("+----------------------+" + cu.RESET);
		
	}
	public void shop(List<Item> inventory, ShoppingCart cart)
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
		System.out.printf(cu.BLUE_BRIGHT + "+----------------------+  Cart %s",
				(cart.size() == 0) ? "Empty\n" : String.format("$%.2f", cart.total())+"\n");
		System.out.printf("|  WELCOME Customer!!! |  \\__/` %d %s",
				cart.size(),(cart.size() > 1 || cart.size() == 0) ? "items\n" : "item\n");
		System.out.println("+----------------------+  O  O" + cu.RESET);
		System.out.print("    Item Name"+ spaces(itemNameLength-9));
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
		System.out.println();
		System.out.println(""+signOutNumber+".  Sign Out");
		System.out.println(""+(signOutNumber + 1)+".  Check Cart");
		System.out.println(""+(signOutNumber + 2)+".  Manage Orders");
		System.out.println();
		enterChoice(signOutNumber - 1, false);
	}
	
	public void cart(ShoppingCart cart)
	{
		int signOutNumber = 0;
		int itemNameLength = 0;
		int itemCodeLength = 0;
		int itemCountLength = 0;
		System.out.println(cart.getItemCountLength());
		for (Item item : cart.getItems())
		{
			if(item.getItemName().length() >= itemNameLength) 
				itemNameLength = item.getItemName().length();
			if(item.getItemCode().length() >= itemCodeLength)
				itemCodeLength = item.getItemCode().length();
		}
		System.out.printf(cu.BLUE_BRIGHT + "+----------------------+  Cart %s",
				(cart.size() == 0) ? "Empty\n" : String.format("$%.2f", cart.total())+"\n");
		System.out.printf("|   Cart Information   |  \\__/` %d %s",
				cart.size(),(cart.size() > 1 || cart.size() == 0) ? "items\n" : "item\n");
		System.out.println("+----------------------+  O  O" + cu.RESET);
		System.out.print(spaces(cart.getItemCountLength())+"  Item Name"+ spaces(itemNameLength-9));
		System.out.print("Code"+spaces(itemCodeLength-4));
		System.out.print("Price");
		System.out.println();
		for (int i = 0; i < cart.getItems().size(); ++i)
		{
			item = cart.getItems().get(i);
			itemCountLength = String.format("%d",item.getItemCount()).length();
			System.out.println(" "+(item.getItemCount())+"x"+spaces(cart.getItemCountLength()-itemCountLength)+item.getItemName() +spaces(itemNameLength-item.getItemName().length())+ item.getItemCode() 
				+ spaces(itemCodeLength-item.getItemCode().length()) + "$" +String.format("%.2f",item.getItemPrice()*item.getItemCount()));
			signOutNumber = i+2;
		}
		System.out.println();
		System.out.println(spaces(itemNameLength+itemCodeLength+cart.getItemCountLength())+"Total:"+"$" +String.format("%.2f",cart.total()));
		System.out.println(""+1+".  Check out");
		System.out.println(""+2+".  Continue Shopping");
		System.out.println();
		enterChoice(signOutNumber - 2, true);
	}
	
	public void showInvoice(Invoice invoice)
	{
		int itemNameLength = 0;
		int itemCodeLength = 0;
		int itemCountLength = 0;
		for (Item item : invoice.getItems())
		{
			if(item.getItemName().length() >= itemNameLength) 
				itemNameLength = item.getItemName().length();
			if(item.getItemCode().length() >= itemCodeLength)
				itemCodeLength = item.getItemCode().length();
		}
		System.out.println(cu.BLUE_BRIGHT + "+----------------------+");
		System.out.println("|  Invoice Information |");
		System.out.println("+----------------------+" + cu.RESET);
		System.out.printf("Customer Name : %s | Date : %s\n",invoice.getUserName(),invoice.getCreationDate().format(formatter));
		System.out.printf("Invoice Number : %d\n", invoice.getInvNumber());
		System.out.println("Items Purchased\n");
		System.out.print(spaces(invoice.getItemCountLength())+"  Name"+ spaces(itemNameLength-4));
		System.out.print("Code"+spaces(itemCodeLength-4));
		System.out.print("Price");
		System.out.println();
		for (int i = 0; i < invoice.getItems().size(); ++i)
		{
			item = invoice.getItems().get(i);
			itemCountLength = String.format("%d",item.getItemCount()).length();
			System.out.println(" "+(item.getItemCount())+"x"+spaces(invoice.getItemCountLength()-itemCountLength)+item.getItemName() +spaces(itemNameLength-item.getItemName().length())+ item.getItemCode() 
				+ spaces(itemCodeLength-item.getItemCode().length()) + "$" +String.format("%.2f",item.getItemPrice()*item.getItemCount()));
		}
		System.out.println();
		System.out.println(spaces(itemNameLength+itemCodeLength+invoice.getItemCountLength())+"Total:"+"$" +String.format("%.2f",invoice.getTotal()));
		System.out.println(""+1+". Continue Shopping");
		System.out.println(""+2+". Sign Out");
		System.out.println();
		enterChoice(2, true);
	}
	public void changeInvoice(Invoice invoice)
	{
		int itemNameLength = 0;
		int itemCodeLength = 0;
		int itemCountLength = 0;
		for (Item item : invoice.getItems())
		{
			if(item.getItemName().length() >= itemNameLength) 
				itemNameLength = item.getItemName().length();
			if(item.getItemCode().length() >= itemCodeLength)
				itemCodeLength = item.getItemCode().length();
		}
		System.out.println(cu.BLUE_BRIGHT + "+----------------------+");
		System.out.println("|    Change Invoice    |");
		System.out.println("+----------------------+" + cu.RESET);
		System.out.printf("Customer Name : %s | Date : %s\n",invoice.getUserName(),invoice.getCreationDate().format(formatter));
		System.out.printf("Invoice Number : %d\n", invoice.getInvNumber());
		System.out.println("Items Purchased\n");
		System.out.print(spaces(invoice.getItemCountLength())+"  Name"+ spaces(itemNameLength-4));
		System.out.print("Code"+spaces(itemCodeLength-4));
		System.out.print("Price");
		System.out.println();
		for (int i = 0; i < invoice.getItems().size(); ++i)
		{
			item = invoice.getItems().get(i);
			itemCountLength = String.format("%d",item.getItemCount()).length();
			System.out.println(" "+(item.getItemCount())+"x"+spaces(invoice.getItemCountLength()-itemCountLength)+item.getItemName() +spaces(itemNameLength-item.getItemName().length())+ item.getItemCode() 
				+ spaces(itemCodeLength-item.getItemCode().length()) + "$" +String.format("%.2f",item.getItemPrice()*item.getItemCount()));
		}
		System.out.println();
		System.out.println(spaces(itemNameLength+itemCodeLength+invoice.getItemCountLength())+"Total:"+"$" +String.format("%.2f",invoice.getTotal()));
		System.out.println("Enter the Item Code to return");
		System.out.println();
	}
	
	public void returnOption()
	{
		System.out.println(cu.BLUE_BRIGHT + "+----------------------+");
		System.out.println("|     Return Item      |");
		System.out.println("+----------------------+" + cu.RESET);
		System.out.println(" 1. Return Item");
		System.out.println(" 2. Continue Shopping");
		System.out.println(" 3. Sign Out");
	}
	
	public void orderHistory(List<Invoice> orderList)
	{
		int signOutNumber = 0;
		System.out.println(cu.BLUE_BRIGHT + "+----------------------+");
		System.out.println("|     Order History    |");
		System.out.println("+----------------------+" + cu.RESET);
		System.out.println("Invoice List:");
		int i = 1;
		for (Invoice invoice : orderList)
		{
			
			System.out.println(" "+ (i)+". Invoice No. [ "+ invoice.getInvNumber()+" ] Total: $" +String.format("%.2f",invoice.getTotal()) + "  Date: " + invoice.getCreationDate().format(formatter));
			i++;
			signOutNumber = i+2;
		}
		System.out.println("Enter the Invoice that you'd like to change");
	}
	
	public String spaces(int i)
	{
		String space = " ";
		try
		{
			space = space.repeat(i + 2);
		} 
		catch (Exception e)
		{
			
		}
		
		
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
	
	public void enterChoice(int num, boolean option)
	{
		System.out.printf(cu.GREEN_BRIGHT + "%s",(option) ? "Enter Choice (" : "Add to Cart (");
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
		
	}
}

