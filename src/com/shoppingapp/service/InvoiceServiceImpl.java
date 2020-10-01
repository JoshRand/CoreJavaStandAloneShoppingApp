package com.shoppingapp.service;

import java.util.List;

import com.shoppingapp.dao.InvoiceDao;
import com.shoppingapp.dao.InvoiceDaoImpl;
import com.shoppingapp.model.Invoice;
import com.shoppingapp.utility.FileStorageUtility;


public class InvoiceServiceImpl implements InvoiceService
{
	InvoiceDao invoiceDao = new InvoiceDaoImpl();
	FileStorageUtility fsu = new FileStorageUtility();
	
	@Override
	public List<Invoice> getInvoices()
	{
		return invoiceDao.getInvoices();
	}

	@Override
	public void saveInvoice(Invoice invoice, boolean fileStream)
	{
		if(!fileStream)
			invoiceDao.save(invoice);
		else
			fsu.saveInvoiceToFile(invoice);
		
	}

	@Override
	public void updateInvoice(int invNumber, String userName, double total, String itemCode, int itemCount)
	{
		invoiceDao.update(invNumber, userName, total, itemCode, itemCount);
		
	}

	@Override
	public List<Invoice> getInvoices(boolean fileStream)
	{
		return fsu.getInvoices();
	}

	@Override
	public void updateInvoiceList(List<Invoice> invoices)
	{
//		for (Invoice invoice : invoices)
//		{
//			System.out.println(invoice.toString());
//		}
		
		fsu.updateAllInvoicesToFile(invoices);
		
	}

	
}
