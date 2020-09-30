package com.shoppingapp.utility;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;  // Import the File class
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.JsonString;
import com.shoppingapp.model.Customer;
import com.shoppingapp.model.Invoice;
import com.shoppingapp.model.Item;



public class FileStorageUtility
{
	private String cus = "Customers.txt";
	private String inv = "Inventory.txt";
	private String invoice = "Invoices.txt";
	
	public void saveCustToFile(Customer cust)
	{
		String fileName = "Customers.txt";
		if(!writeCustToFile(fileName,cust))
			System.out.println("User Exists");
		
	}
	private boolean writeCustToFile(String fileName, Customer cust)
	{
		if(!checkCustExists(cust.getUserName()))
		{
			try {
		      PrintWriter myWriter = new PrintWriter(new FileWriter(fileName,true));
		      myWriter.append(custToString(cust));
		      myWriter.close();
		      return true;
		    } catch (IOException e) {
		    	 return false;
		    }
		}
		return false;
	}
	
	private String custToString(Customer cust)
	{
		String structure = "{\n"
				+ "userName:"+cust.getUserName()+",\n"
				+ "userPass:"+cust.getUserPass()+",\n"
				+ "},\n";
		return structure;
	}
	public List<Item> getInventory()
	{
		Item item = null;
		List<Item> list = new ArrayList<Item>();
		String workingString = "";
		String contents = readFile(inv);
		System.out.println(contents);
		String[] tokens = contents.split("[{s},]+");
		for (String string : tokens)
		{
			
			if(string.contains("itemName:"))
			{
				item = new Item();
				string = string.replace("itemName:", "");
				item.setItemName(string);
				System.out.println(item.getItemName());
			}
			if(string.contains("itemCode:"))
			{
				string = string.replace("itemCode:", "");
				item.setItemCode(string);
			}
			if(string.contains("itemPrice:"))
			{
				string = string.replace("itemPrice:", "");
				item.setItemPrice(Double.parseDouble(string));
			}
			if(string.contains("itemCount:"))
			{
				string = string.replace("itemCount:", "");
				item.setItemCount(Integer.parseInt(string));
				list.add(item);
			}
			System.out.println(string);
		}
		
		System.out.println(list.toString());
		return list;
	}
	private String invoiceToString(Invoice invoice)
	{
		String structure = "{\n"
				+ "userName:"+invoice.getUserName()+",\n"
				+ "userName:"+invoice.getInvNumber()+",\n"
				+ "},\n";
		return structure;
	}
	
	public Customer getCustomer(String userName)
	{
		String uName = "";
		String uPass = "";
		
		String contents = readFile(cus);
		String lowerContents = contents.toLowerCase();
		String condition = ("username:"+userName.toLowerCase()+",userpass:");
		int j = lowerContents.indexOf(condition);
		int i = condition.lastIndexOf("userpass:");
		i = j+i;
		//System.out.println(i+j);
		while(contents.charAt(i) != ',')
		{
			uPass += contents.charAt(i);
			i++;
		}
		uPass = uPass.replace("userPass:", "");
		uPass = uPass.trim();
		//System.out.println("pass is Filestream : "+uPass);
		return new Customer(userName,uPass);
	}
	
	private boolean checkCustExists(String email)
	{
		String contents = readFile(cus);
		// check string contents next
		if(contents.toLowerCase().contains("username:"+email.toLowerCase()+","))
		{
			return true;
		}
		return false;
	}
	private String readFile(String fileName)
	{
		String fileRead = "";
		try
		{
			BufferedReader buffReader = new BufferedReader(new FileReader(fileName)); 
			String st; 
			while ((st = buffReader.readLine()) != null) 
				fileRead+=st;
			return fileRead;
		} 
		catch (Exception e)
		{
			System.out.println("ReadFileError");
			return "";
		}
		
	}
}
