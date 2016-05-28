package com.trioplus.sm.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.trioplus.sm.plus.process.exception.ProcessException;
import com.trioplus.sm.process.dao.MainDAO;
import com.trioplus.sm.process.model.CustomerShareCount;
import com.trioplus.sm.process.model.SocialMediaConfig;

/**
 * Hello world!
 *
 */
public class App {

	public final static String SOCIAL_MEDIA_CONFIG = "SOCIAL_MEDIA_CONFIG";

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("com/trioplus/sm/process/config/spring.xml");
		MainDAO dao = (MainDAO) context.getBean("mainDao");
		try {
			SocialMediaConfig config = dao.getConfig(new Long(350), SOCIAL_MEDIA_CONFIG);
			List<CustomerShareCount> eligibleList = new ArrayList<CustomerShareCount>();
			eligibleList = dao.getEligibleCustomers(config);
			for (CustomerShareCount customer : eligibleList) {
				dao.updateCustomer(customer, config);
			}

		} catch (ProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
