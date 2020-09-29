package com.shoppingapp.service;

import java.util.List;

import com.shoppingapp.dao.InvoiceDao;
import com.shoppingapp.dao.InvoiceDaoImpl;
import com.shoppingapp.model.Invoice;


public class InvoiceServiceImpl implements InvoiceService
{
	InvoiceDao invoiceDao = new InvoiceDaoImpl();
	
	@Override
	public List<Invoice> getInvoices()
	{
		return invoiceDao.getInvoices();
	}

	@Override
	public void saveInvoice(Invoice invoice)
	{
		invoiceDao.save(invoice);
		
	}

	@Override
	public void updateInvoice(int invNumber, String userName, double total, String itemCode, int itemCount)
	{
		invoiceDao.update(invNumber, userName, total, itemCode, itemCount);
		
	}

	
}
