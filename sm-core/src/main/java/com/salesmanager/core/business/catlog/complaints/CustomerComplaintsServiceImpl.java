package com.salesmanager.core.business.catlog.complaints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.complaint.model.ComplaintsReason;
import com.salesmanager.core.business.complaint.model.CustomerComplaint;
import com.salesmanager.core.business.complaints.dao.ComplaintsDao;
import com.salesmanager.core.business.complaints.dao.ComplaintsReasonDAO;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.reference.language.model.Language;
@Service("customerComplaintsService")
public class CustomerComplaintsServiceImpl extends SalesManagerEntityServiceImpl<Long, CustomerComplaint> implements CustomerComplaintsService {
	private ComplaintsDao complaintsDao;
	@Autowired
	public CustomerComplaintsServiceImpl(
			ComplaintsDao complaintsDao) {
		super(complaintsDao);
		this.complaintsDao=complaintsDao;
	}

	@Override
	public List<CustomerComplaint> listByStore(
			) throws ServiceException {
		try {
			return complaintsDao.listComplaintsReason();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void saveOrUpdate(CustomerComplaint customerComplaint) throws ServiceException {
		
				if(customerComplaint.getId()!=null && customerComplaint.getId()>0) {

					super.update(customerComplaint);
					
				} else {
					
					super.save(customerComplaint);
					
				}
		
	}

	@Override
	public List<CustomerComplaint> getByName(String categoryName,String status
			) throws  ServiceException{
		try {
			return complaintsDao.getByName( categoryName,status);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		
	}

	@Override
	public List<CustomerComplaint> getByName(String note, String status,
			String customerMail, String reason, String date) throws ServiceException {
		// TODO Auto-generated method stub
		try {
		return complaintsDao.getByName(note,status,customerMail,reason,date);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}