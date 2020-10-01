package com.shoppingapp.service;

import java.util.List;

import com.shoppingapp.model.Invoice;


public interface InvoiceService
{
	public List<Invoice> getInvoices();
	public List<Invoice> getInvoices(boolean fileStream);
	public void saveInvoice(Invoice invoice, boolean fileStream);
	public void updateInvoice(int invNumber, String userName, double total, String itemCode, int itemCount);
	public void updateInvoiceList(List<Invoice> invoices);
	
}
