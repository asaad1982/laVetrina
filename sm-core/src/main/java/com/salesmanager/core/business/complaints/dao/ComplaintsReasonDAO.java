package com.salesmanager.core.business.complaints.dao;


import java.util.List;

import com.salesmanager.core.business.complaint.model.ComplaintsReason;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.reference.language.model.Language;

public interface ComplaintsReasonDAO extends SalesManagerEntityDao<Long, ComplaintsReason> {
public List<ComplaintsReason> listComplaintsReason();

public List<ComplaintsReason> getByName(String categoryName, Language language) ;
}
