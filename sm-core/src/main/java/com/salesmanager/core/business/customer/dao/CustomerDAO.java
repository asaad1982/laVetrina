package com.salesmanager.core.business.customer.dao;

import java.util.List;

import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.customer.model.CustomerCriteria;
import com.salesmanager.core.business.customer.model.CustomerList;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;

public interface CustomerDAO extends SalesManagerEntityDao<Long, Customer> {
	
	public List<Customer> getByName(String name);

	List<Customer> listByStore(MerchantStore store);

	Customer getByNick(String nick);

	CustomerList listByStore(MerchantStore store, CustomerCriteria criteria);

	Customer getByNick(String nick, int storeId);

	List<Customer> getBySearchCritera(String firstname, String lastname,
			String gender, String birthDate, String country,String email);

	Customer getByEmail(String email);

	public Customer getByEmail(String email, Integer id);
	
	public void updateCustomerShare(Customer customer);
	
}
