package com.shoppingapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.shoppingapp.model.Customer;

public class UserDaoImpl implements UserDao
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
	public void create(Customer cust)
	{
		try
		{
			Customer custSearch = findByUserName(cust.getUserName());
			if(custSearch == null)
			{
				Connection con = getConnection();
				Statement statemnt = con.createStatement();
				statemnt.execute("insert into customers(userName,userPass,invoiceCount)values('"
						+cust.getUserName()+"','"+cust.getUserPass()+"','"+cust.getInvoiceCount()+"')");
				
			}
		
		} 
		catch (Exception e)
		{
			System.out.println("Create Exception");
		}
	}

	@Override
	public void update(Customer cust)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByName()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer findByUserName(String userName)
	{
		Customer cust = null;
		try
		{
			Connection con = getConnection();
		
			PreparedStatement ps = con.prepareStatement(
					"select * from customers where userName = ?");
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			System.out.println("inside find by userName (mysql)");
			if(rs.next()) 
			{
				cust = new Customer(rs.getString(1),rs.getString(2));
				return cust;
			}
			else
			{	
				return null;
			}
			
		} catch (Exception e)
		{
			System.out.println("find by UserName Exception");
		}
		return null;
	}
}
