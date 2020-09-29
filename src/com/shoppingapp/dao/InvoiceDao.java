package com.shoppingapp.dao;

import java.util.List;

import com.shoppingapp.model.Invoice;

public interface InvoiceDao
{
	public List<Invoice> getInvoices();
	public void save(Invoice invoice);
	public void update(int invNumber, String userName, double total, String itemCode, int itemCount);
}
