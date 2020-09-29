package com.shoppingapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shoppingapp.model.Customer;
import com.shoppingapp.model.Invoice;
import com.shoppingapp.model.Item;

public class InvoiceDaoImpl implements InvoiceDao
{
	public static Connection getConnection() 
	{
		Connection conn = null;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			//?"+"autoReconnect=true&useSSL=false
			conn = DriverManager.getConnection("jdbc:mysql://localhost/shopappdb","root","root" );
			
			return conn;
		}
		catch(Exception e) 
		{
			
		}
		return conn;
		
	}
	
	@Override
	public List<Invoice> getInvoices()
	{
		Invoice invoice = null;
		List<Invoice> invoices = new ArrayList<Invoice>();
		List<Item> items = new ArrayList<Item>();
		try
		{
			Connection con = getConnection();
		
			PreparedStatement psInvoices = con.prepareStatement(
					"select * from invoices");
			
			ResultSet rs = psInvoices.executeQuery();
			// Gets all invoices
			while(rs.next()) 
			{
				invoice = new Invoice(rs.getString(4), new ArrayList<Item>(), rs.getDouble(3));
				invoice.setInvNumber(rs.getInt(2));
				invoices.add(invoice);
				
			}
			// Gets all invoice items and assigns to the invoices invoice
			for (Invoice invoi : invoices)
			{
				//System.out.println("invoce: "+ invoi.getInvNumber());
				PreparedStatement psInvoiceItems = 
						con.prepareStatement("select * from invoiceitems where invNum ="+invoi.getInvNumber()+" and userName ='"+invoi.getUserName()+"'");
				rs = psInvoiceItems.executeQuery();
				while(rs.next()) 
				{
					items.add(new Item(rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getInt(7)));
					//System.out.println(items.toString());
				}
				invoi.setItems(items);
				items = new ArrayList<Item>();
			}
			return invoices;
		} catch (Exception e)
		{
			System.out.println("get Invoices Exception");
		}
		return null;
	}

	@Override
	public void save(Invoice invoice)
	{
		try
		{
			Connection con = getConnection();
			Statement statemnt = con.createStatement();
			statemnt.execute("insert into invoices(invNumber,total,userName)values('"+invoice.getInvNumber()+"',"+invoice.getTotal()+",'"+invoice.getUserName()+"')");
			
			for (Item item : invoice.getItems())
			{
				statemnt.execute("insert into invoiceitems(invNum,userName,itemName,itemCode,price,itemCount)values('"+invoice.getInvNumber()+"','"+invoice.getUserName()+"','"
						+item.getItemName()+"','"+item.getItemCode()+"','"+item.getItemPrice()+"','"+item.getItemCount()+"')");
			}
		
		} 
		catch (Exception e)
		{
			System.out.println("save invoice Exception");
		}
		
	}

	@Override
	public void update(int invNumber, String userName, double total, String itemCode, int itemCount)
	{
		try
		{
			Connection con = getConnection();
			Statement statemnt = con.createStatement();
			statemnt.execute("update invoices set total = '"+total+"' where invNumber ='"+invNumber+"' and userName = '"+userName+"'");
			statemnt.execute("update invoiceitems set itemCount = '"+itemCount+"' where invNum ='"+invNumber+"' and userName = '"+userName+"' and itemCode = '"+itemCode+"'");
		
		} 
		catch (Exception e)
		{
			System.out.println("update invoice Exception");
		}
		
	}
}
