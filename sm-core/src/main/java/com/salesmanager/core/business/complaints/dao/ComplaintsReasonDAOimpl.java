package com.salesmanager.core.business.complaints.dao;

import java.util.List;













import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.model.QCategory;
import com.salesmanager.core.business.catalog.category.model.QCategoryDescription;
import com.salesmanager.core.business.complaint.model.ComplaintsReason;
import com.salesmanager.core.business.complaint.model.QComplaintsReason;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.reference.language.model.Language;
@Repository("complaintsReasonDAO")
public class ComplaintsReasonDAOimpl extends SalesManagerEntityDaoImpl<Long, ComplaintsReason> implements ComplaintsReasonDAO{

	@Override
	public List<ComplaintsReason> listComplaintsReason() {
		QComplaintsReason qComplaintsReason = QComplaintsReason.complaintsReason;
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qComplaintsReason)
			
			.orderBy(qComplaintsReason.id.asc());
		
		return query.distinct().list(qComplaintsReason);
		
	}

	@Override
	public List<ComplaintsReason> getByName(String categoryName,
			Language language) {
		QComplaintsReason qComplaintsReason = QComplaintsReason.complaintsReason;
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qComplaintsReason)
			
			.where(qComplaintsReason.englishName.like("%" + categoryName + "%")
			.or(qComplaintsReason.arabicName.like("%" + categoryName + "%")))
			;
		

		
		List<ComplaintsReason> categories = query.list(qComplaintsReason);
		return categories;
	}

	
}
