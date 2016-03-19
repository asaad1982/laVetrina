package com.salesmanager.core.business.complaints.dao;


import java.util.Date;
import java.util.List;







import com.salesmanager.core.business.complaint.model.CustomerComplaint;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;


public interface ComplaintsDao extends SalesManagerEntityDao<Long, CustomerComplaint>{
	public List<CustomerComplaint> listComplaintsReason();

	public List<CustomerComplaint> getByName(String categoryName,String status) ;

	

	public List<CustomerComplaint> getByName(String note, String status,
			String customerMail, String reason, String date);

	
}
