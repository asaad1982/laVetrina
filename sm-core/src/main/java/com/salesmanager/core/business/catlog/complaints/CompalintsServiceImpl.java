package com.salesmanager.core.business.catlog.complaints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.catalog.category.dao.CategoryDao;
import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.complaint.model.ComplaintsReason;
import com.salesmanager.core.business.complaints.dao.ComplaintsReasonDAO;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
@Service("compalintsService")
public class CompalintsServiceImpl extends SalesManagerEntityServiceImpl<Long, ComplaintsReason> implements ComplaintsService {
	private ComplaintsReasonDAO complaintsReasonDAO;
	@Autowired
	public CompalintsServiceImpl(
			ComplaintsReasonDAO complaintsReasonDAO) {
		super(complaintsReasonDAO);
		this.complaintsReasonDAO=complaintsReasonDAO;
	}

	@Override
	public List<ComplaintsReason> listByStore(
			Language language) throws ServiceException {
		try {
			return complaintsReasonDAO.listComplaintsReason();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void saveOrUpdate(ComplaintsReason category) throws ServiceException {
		//save or update (persist and attach entities
				if(category.getId()!=null && category.getId()>0) {

					super.update(category);
					
				} else {
					
					super.save(category);
					
				}
		
	}

	@Override
	public List<ComplaintsReason> getByName(String categoryName,
			Language language) throws  ServiceException{
		try {
			return complaintsReasonDAO.getByName( categoryName, language);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		
	}



}
