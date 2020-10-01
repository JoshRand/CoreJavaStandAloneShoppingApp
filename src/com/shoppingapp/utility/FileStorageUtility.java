package com.shoppingapp.utility;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.shoppingapp.model.Customer;
import com.shoppingapp.model.Invoice;
import com.shoppingapp.model.Item;



public class FileStorageUtility
{
	private String cus = "Customers.txt";
	private String inv = "Inventory.txt";
	private String invo = "Invoices.txt";
	public void saveCustToFile(Customer cust)
	{

		if(!writeCustToFile(cus,cust))
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
	public void saveInvoiceToFile(Invoice invoice)
	{
//		if(checkInvoiceExists(invoice))
//		{
//			//writeInvoiceToFile(invo, invoice, invoiceOffset);
//		}
//		else
//		{
			writeInvoiceToFile(invo, invoice);
	//	}
		
	}
	private boolean writeInvoiceToFile(String fileName, Invoice invoice)
	{	
		try {
	      PrintWriter myWriter = new PrintWriter(new FileWriter(fileName,true));
	      myWriter.append(invoiceToString(invoice));
	      myWriter.close();
	      return true;
	    } catch (IOException e) {
	    	 return false;
	    }
	
	}
	public void updateAllInvoicesToFile(List<Invoice> invoices)
	{	
		try {
	      FileWriter myWriter = new FileWriter(invo);
	      for (Invoice invoice : invoices)
	      {
	    	  myWriter.write(invoiceToString(invoice));
	      }
	      myWriter.close();
	   
	    } catch (IOException e) {
	    	
	    }
	
	}
	public void updateAllCustomersToFile(List<Customer> custs)
	{	
		try {
	      FileWriter myWriter = new FileWriter(cus);
	      for (Customer cust : custs)
	      {
	    	  myWriter.write(custToString(cust));
	      }
	      myWriter.close();
	   
	    } catch (IOException e) {
	    	
	    }
	
	}
	private String custToString(Customer cust)
	{
		String structure = "{\n"
				+ "userName:"+cust.getUserName()+",\n"
				+ "userPass:"+cust.getUserPass()+",\n"
				+ "invoiceCount:"+cust.getInvoiceCount()+",\n"
				+ "},\n";
		return structure;
	}
	
	public List<Customer> getCustomers()
	{
		Customer cust = null;
		List<Customer> list = new ArrayList<Customer>();
		String contents = readFile(cus);
		//System.out.println(contents);
		String[] tokens = contents.split("[{},]+");
		for (String string : tokens)
		{
			
			if(string.contains("userName:"))
			{
				cust = new Customer();
				string = string.replace("userName:", "");
				cust.setUserName(string);
			}
			if(string.contains("userPass:"))
			{
				string = string.replace("userPass:", "");
				cust.setUserPass(string);
			}if(string.contains("invoiceCount:"))
			{
				string = string.replace("invoiceCount:", "");
				cust.setInvoiceCount(Integer.parseInt(string));
				list.add(cust);
			}
			//System.out.println(string);
		}
		
		return list;
	}
	
	public List<Item> getInventory()
	{
		Item item = null;
		List<Item> list = new ArrayList<Item>();
		String contents = readFile(inv);
		//System.out.println(contents);
		String[] tokens = contents.split("[{},]+");
		for (String string : tokens)
		{
			
			if(string.contains("itemName:"))
			{
				item = new Item();
				string = string.replace("itemName:", "");
				item.setItemName(string);
				//System.out.println(item.getItemName());
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
			//System.out.println(string);
		}
		
		//System.out.println(list.toString());
		return list;
	}
	
	public List<Invoice> getInvoices()
	{
		Item item = null;
		Invoice invoice = null;
		List<Item> items = new ArrayList<Item>();
		List<Invoice> invoices = new ArrayList<Invoice>();
		String contents = readFile(invo);
		//System.out.println(contents);
		String[] tokens = contents.split("[{ \t},]+");
		
		for (String string : tokens)
		{
			if(string.contains(":itemsEnd"))
			{
				invoice.setItems(items);
				items = new ArrayList<Item>();
				invoices.add(invoice);
			}
			if(string.contains("invNumber:"))
			{
				invoice = new Invoice();
				string = string.replace("invNumber:", "");
				invoice.setInvNumber(Integer.parseInt(string));
				//System.out.println(invoice.getInvNumber());
			}
			if(string.contains("total:"))
			{
				
				string = string.replace("total:", "");
				invoice.setTotal(Double.parseDouble(string));
				//System.out.println(invoice.getTotal());
			}
			if(string.contains("userName:"))
			{
				
				string = string.replace("userName:", "");
				invoice.setUserName(string);
				//System.out.println(invoice.getUserName());
			}
			if(string.contains("creationDate:"))
			{
				string = string.replace("creationDate:", "");
				invoice.setCreationDate(LocalDateTime.parse(string));
				//System.out.println(invoice.getCreationDate());
			}
			
			if(string.contains("itemName:"))
			{
				item = new Item();
				string = string.replace("itemName:", "");
				item.setItemName(string);
				//System.out.println(item.getItemName());
			}
			if(string.contains("itemCode:"))
			{
				string = string.replace("itemCode:", "");
				item.setItemCode(string);
			}
			if(string.contains("itemPrice:"))
			{
				string = string.replace("itemPrice:", "");
				
				//System.out.println(string);
				item.setItemPrice(Double.parseDouble(string));
			}
			if(string.contains("itemCount:"))
			{
				string = string.replace("itemCount:", "");
				item.setItemCount(Integer.parseInt(string));
				items.add(item);
				
			}
			//System.out.println(string);
		}
//		for (Invoice invs : invoices)
//		{
//			System.out.println(invs.toString());
//		}
//		System.out.println(invoices.toString());
		return invoices;
	}
	
	private String invoiceToString(Invoice invoice)
	{
		String structure = "{\n"
				+ "invNumber:"+invoice.getInvNumber()+",\n"
				+ "total:"+invoice.getTotal()+",\n"
				+ "userName:"+invoice.getUserName()+",\n"
				+ "creationDate:"+invoice.getCreationDate()+",\n"
				+ "items:[\n\t";
		
		for (Item item : invoice.getItems())
		{
			structure += "{\n";
			structure += "\titemName:"+item.getItemName()+"\n\t";
			structure += "itemCode:"+item.getItemCode()+"\n\t";
			structure += "itemPrice:"+item.getItemPrice()+"\n\t";
			structure += "itemCount:"+item.getItemCount()+"\n\t}\n\t";
		}
		structure  +=  "\n:itemsEnd]\n},";
		return structure;
	}
	
	public Customer getCustomer(String userName)
	{
		String uPass = "";
		String invCountStr = "";
		int invCount = 0;
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
		i++;
		while(contents.charAt(i) != ',')
		{
			invCountStr+=contents.charAt(i);
			i++;
		}
		invCountStr = invCountStr.replace("invoiceCount:", "");
		invCountStr = invCountStr.trim();
		invCount = Integer.parseInt(invCountStr);
//		System.out.println("pass is Filestream : "+uPass);
//		System.out.println("Invoice count = " + invCount);
		Customer cust = new Customer(userName, uPass);
		cust.setInvoiceCount(invCount);
		return cust;
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
	public boolean checkInvoiceExists(Invoice invoice)
	{
		List<Invoice> list = getInvoices();
		
		for (Invoice invoice2 : list)
		{
			if(invoice2.getInvNumber() == invoice.getInvNumber()
					&& invoice2.getUserName().equalsIgnoreCase(invoice.getUserName()))
			{
				System.out.println("Invoice exists");
				return true;
			}
		}
		System.out.println("Invoice doesnn't exists");
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
