package com.shoppingapp.service;

import java.util.List;

import com.shoppingapp.model.Invoice;


public interface InvoiceService
{
	public List<Invoice> getInvoices();
	public void saveInvoice(Invoice invoice);
	public void updateInvoice(int invNumber, String userName, double total, String itemCode, int itemCount);
	
}
