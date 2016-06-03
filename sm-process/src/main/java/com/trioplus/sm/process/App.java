package com.trioplus.sm.process;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.velocity.app.VelocityEngine;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.trioplus.sm.plus.process.exception.ProcessException;
import com.trioplus.sm.process.dao.MainDAO;
import com.trioplus.sm.process.model.CustomerShareCountModel;
import com.trioplus.sm.process.model.MailModel;
import com.trioplus.sm.process.model.SocialMediaConfigModel;

/**
 * Hello world!
 *
 */
public class App {

	public final static String SOCIAL_MEDIA_CONFIG = "SOCIAL_MEDIA_CONFIG";
	final static Logger logger = Logger.getLogger(App.class);
	final static Properties prop = new Properties();
	static ApplicationContext context;

	
	
	
	/**
	 * Load job configuration file
	 * 
	 * @throws ProcessException
	 */

	private static void getJobConfiguration() throws ProcessException {
		try {
			logger.debug("Job has been started, loading configuration ");
			InputStream in = App.class.getResourceAsStream("/com/trioplus/sm/process/config/job.properties");
			prop.load(in);
			String log4jConfPath = prop.getProperty("log4j.fileName");
			PropertyConfigurator.configure(log4jConfPath);
			context = new ClassPathXmlApplicationContext("com/trioplus/sm/process/config/spring.xml");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Error while getting process configuration job will be aborted");
			System.exit(0);

		}

	}

	/**
	 * Job main method
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		try {
			getJobConfiguration();
			MainDAO dao = (MainDAO) context.getBean("mainDao");
			logger.debug("Getting social sharing discount from DB");
			SocialMediaConfigModel config = dao.getConfig(new Long(350), SOCIAL_MEDIA_CONFIG);
			logger.debug("loading configuration has been ended successfully ");
			logger.debug("Getting eligeible customers ");
			List<CustomerShareCountModel> eligibleList = new ArrayList<CustomerShareCountModel>();
			eligibleList = dao.getEligibleCustomers(config);
			logger.debug("Eligible customers are loaded successfully , size is = "+ eligibleList.size());
			for (CustomerShareCountModel customer : eligibleList) {
				logger.debug("customer ID : " + customer.getId() + " is eligible, customer log will be updated");
				dao.updateCustomer(customer, config);
				logger.debug("customer ID : " + customer.getId() + " has been updated successfully");
				logger.debug("customer ID : " + customer.getId() + " starting to send email to customer");
				MailModel mail= new MailModel();
				mail.setFrom(prop.getProperty("mail.sender"));
				mail.setTo(customer.getCustomerEmail());
				mail.setSubject(prop.getProperty("mail.subject"));
				SendHTMLEmail(mail,customer);
				logger.debug("customer ID : " + customer.getId() +" mail has been sent successfully to customer");
			}

		} catch (Exception e) {
			//throw new ProcessException(e.getMessage());
			logger.error(e.getStackTrace());
		}
	}


/**
 * Method is sendinh HTML mail
 * @param mail
 */
	
	
	private static  void SendHTMLEmail(final MailModel mail, final CustomerShareCountModel customer){
		VelocityEngine  velocityEngine = (VelocityEngine ) context.getBean("velocityEngine");
		Map model = new HashMap();
		model.put("user", "aysss7aga");
        
		
		// Getting HTML template
		String messageBoy = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, "com/trioplus/sm/process/config/mail-template.vm", model);
        
		
		mail.setMessgae(messageBoy);
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
        	
           
            public void prepare(MimeMessage mimeMessage) throws Exception {
            	
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                message.setTo(mail.getTo());
                message.setFrom(new InternetAddress(mail.getFrom()));
                message.setSubject(mail.getSubject());                  
                message.setText(mail.getMessgae(), true);                 
            }
        };
        JavaMailSender	mailSender   = (JavaMailSender) context.getBean("mailSender");
		mailSender.send(preparator);
    }
	
	
	
}
