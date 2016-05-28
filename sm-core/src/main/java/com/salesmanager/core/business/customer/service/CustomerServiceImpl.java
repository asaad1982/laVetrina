package com.salesmanager.core.business.customer.service;

import java.awt.Graphics2D;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.jopendocument.model.OpenDocument;
import org.jopendocument.renderer.ODTRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductAttribute;
import com.salesmanager.core.business.catalog.product.model.availability.ProductAvailability;
import com.salesmanager.core.business.catalog.product.model.manufacturer.ManufacturerDescription;
import com.salesmanager.core.business.common.model.Address;
import com.salesmanager.core.business.customer.dao.CustomerDAO;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.customer.model.CustomerCriteria;
import com.salesmanager.core.business.customer.model.CustomerList;
import com.salesmanager.core.business.customer.model.attribute.CustomerAttribute;
import com.salesmanager.core.business.customer.service.attribute.CustomerAttributeService;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.modules.utils.GeoLocation;

@Service("customerService")
public class CustomerServiceImpl extends SalesManagerEntityServiceImpl<Long, Customer> implements CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	private CustomerDAO customerDAO;
	
	@Autowired
	private CustomerAttributeService customerAttributeService;
	
	@Autowired
	private GeoLocation geoLocation;

	
	@Autowired
	public CustomerServiceImpl(CustomerDAO customerDAO) {
		super(customerDAO);
		this.customerDAO = customerDAO;
	}

	@Override
	public List<Customer> getByName(String firstName) {
		return customerDAO.getByName(firstName);
	}
	
	@Override
	public Customer getById(Long id) {
			return customerDAO.getById(id);		
	}
	
	@Override
	public Customer getByNick(String nick) {
		return customerDAO.getByNick(nick);	
	}
	
	@Override
	public Customer getByEmail(String email) {
		return customerDAO.getByEmail(email);	
	}
	
	@Override
	public Customer getByNick(String nick, int storeId) {
		return customerDAO.getByNick(nick, storeId);	
	}
	
	@Override
	public List<Customer> listByStore(MerchantStore store) {
		return customerDAO.listByStore(store);
	}
	
	@Override
	public CustomerList listByStore(MerchantStore store, CustomerCriteria criteria) {
		return customerDAO.listByStore(store,criteria);
	}
	
	@Override
	public Address getCustomerAddress(MerchantStore store, String ipAddress) throws ServiceException {
		
		try {
			return geoLocation.getAddress(ipAddress);
		} catch(Exception e) {
			throw new ServiceException(e);
		}
		
	}

	@Override	
	public void saveOrUpdate(Customer customer) throws ServiceException {

		LOGGER.debug("Creating Customer");
		
		if(customer.getId()!=null && customer.getId()>0) {
			super.update(customer);
		} else {			
		
			super.create(customer);

		}
	}

	public void delete(Customer customer) throws ServiceException {
		customer = getById(customer.getId());
		
		//delete attributes
		List<CustomerAttribute> attributes =customerAttributeService.getByCustomer(customer.getMerchantStore(), customer);
		if(attributes!=null) {
			for(CustomerAttribute attribute : attributes) {
				customerAttributeService.delete(attribute);
			}
		}
		customerDAO.delete(customer);

	}
	public List<Customer> getBySearchCritera(String firstname, String lastname,
			String gender, String birthDate, String country,String email){
		return customerDAO.getBySearchCritera(firstname, lastname, gender, birthDate, country,email);
	}
	
	@Override
	public ByteArrayOutputStream exportCusstomerList(MerchantStore store,List<Customer> customers, Language language) throws Exception {
		
		

			
			
			
//			//get default template
//			String template = new StringBuilder().append(PRODUCT_TEMPLATE).append("_").append(language.getCode().toLowerCase()).append(INVOICE_TEMPLATE_EXTENSION).toString();
//			
//			//try by language
//			InputStream is = null;
//			try {
//				is = getClass().getClassLoader().getResourceAsStream(template);
//			} catch (Exception e) {
//				LOGGER.warn("Cannot open template " + template);
//				throw new Exception("Cannot open " + template);
//			}
//			
//			if(is==null) {
//				try {
//					is = getClass().getClassLoader().getResourceAsStream(new StringBuilder().append(PRODUCT_TEMPLATE).append(INVOICE_TEMPLATE_EXTENSION).toString());
//				} catch (Exception e) {
//					LOGGER.warn("Cannot open template " + template);
//					throw new Exception("Cannot open " + new StringBuilder().append(PRODUCT_TEMPLATE).append(INVOICE_TEMPLATE_EXTENSION).toString());
//				}
//			}
//			
//			if(is==null) {
//				LOGGER.warn("Cannot open template " + template);
//				throw new Exception("Cannot open " + new StringBuilder().append(PRODUCT_TEMPLATE).append(INVOICE_TEMPLATE_EXTENSION).toString());
//			}
//			
//			File file = new File("Product" + "_working");
//			OutputStream os = new FileOutputStream(file);
//			IOUtils.copy(is, os);
//			os.close();
//			//File file = new File(resource.toURI().toURL());
//			System.out.println("File:"+file);
//			SpreadSheet spreadSheet=SpreadSheet.createFromFile(file);
//			Sheet sheet = spreadSheet.getSheet(0);
//			
//			int i=0;
//			sheet.setValueAt("Product Name", 0, 0);
//			sheet.setValueAt("Item#", 1, 0);
//			sheet.setValueAt("Category", 2, 0);
//			sheet.setValueAt("Brand", 3, 0);
//			sheet.setValueAt("Price", 4, 0);
//			sheet.setValueAt("Stock Quantity", 5, 0);
//			for (Product product : products) {
//				
//			Product product2=getById(product.getId());
//			if(product2!=null){
//				i++;
//			sheet.setValueAt(product2.getProductDescription()!=null?product2.getProductDescription().getName():"", 0, i);
//			sheet.setValueAt(product2.getSku(), 1, i);
//			for (Iterator iterator = product2.getCategories().iterator(); iterator.hasNext();) {
//				Category category = (Category) iterator.next();
//				sheet.setValueAt(category.getDescription().getName(), 2, i);
//				
//			}
//			for (Iterator iterator = product2.getManufacturer().getDescriptions().iterator(); iterator.hasNext();) {
//				ManufacturerDescription manufacturerDescription = (ManufacturerDescription) iterator.next();
//				sheet.setValueAt(manufacturerDescription.getName(), 3, i);
//				
//			}
//			 
//				for (Iterator iterator = product2.getAttributes().iterator(); iterator.hasNext();) {
//					ProductAttribute productAttribute = (ProductAttribute) iterator.next();
//					sheet.setValueAt(productAttribute.getProductAttributePrice(), 4, i);
//					
//					
//				}
//				for (Iterator iterator = product2.getAvailabilities().iterator(); iterator.hasNext();) {
//					ProductAvailability productAvailability = (ProductAvailability) iterator.next();
//					
//					sheet.setValueAt(priceUtil.getAdminFormatedAmountWithCurrency(store,productAvailability.defaultPrice().getProductPriceAmount()), 5, i);
//					
//					
//				}
//			}
//			 
//			}
//			
//			//sheet.getCellAt(0, 0).setImage(arg0)
//			//sheet.getCellAt(0, 0).setStyleName(arg0)
//			//sheet.getCellAt(0, 0).getStyle().
//			
//			
//			//generate invoice file
//			StringBuilder tempProductName = new StringBuilder();
//			tempProductName.append("Product").append(new Date()).append(TEMP_INVOICE_SUFFIX_NAME);
//			File outputFile = new File(tempProductName.toString());
//			OOUtils.open(sheet.getSpreadSheet().saveAs(outputFile));
//			
//			
//			
//			final OpenDocument doc = new OpenDocument();
//			doc.loadFrom(tempProductName.toString());
//
//			 // Open the PDF document
//			 Document document = new Document(PageSize.A4);
//			 
//			 
//			 //File outFile = new File("invoice.pdf");
//
//			 PdfDocument pdf = new PdfDocument();
//
//			 document.addDocListener(pdf);
//
//			 //FileOutputStream fileOutputStream = new FileOutputStream(outFile);
//			 ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//			 
//			 PdfWriter writer = PdfWriter.getInstance(pdf, outputStream);
//			 pdf.addWriter(writer);
//
//			 document.open();
//
//			 // Create a template and a Graphics2D object 
//			 Rectangle pageSize = document.getPageSize();
//			 int w = (int) (pageSize.getWidth() * 0.9);
//			 int h = (int) (pageSize.getHeight() * 0.95);
//			 PdfContentByte cb = writer.getDirectContent();
//			 PdfTemplate tp = cb.createTemplate(w, h);
//
//			 Graphics2D g2 = tp.createPrinterGraphics(w, h, null);
//			 // If you want to prevent copy/paste, you can use
//			 // g2 = tp.createGraphicsShapes(w, h, true, 0.9f);
//			            
//			 tp.setWidth(w);
//			 tp.setHeight(h);
//
//			 // Configure the renderer
//			 ODTRenderer renderer = new ODTRenderer(doc);
//			 renderer.setIgnoreMargins(true);
//			 renderer.setPaintMaxResolution(true);
//			            
//			 // Scale the renderer to fit width
//			 renderer.setResizeFactor(renderer.getPrintWidth() / w);
//			 // Render
//			 renderer.paintComponent(g2);
//			 g2.dispose();
//
//			 // Add our spreadsheet in the middle of the page
//			 float offsetX = (float) ((pageSize.getWidth() - w) / 2);
//			 float offsetY = (float) ((pageSize.getHeight() - h) / 2);
//			 cb.addTemplate(tp, offsetX, offsetY);
//			 // Close the PDF document
//			 document.close();
//			 outputFile.delete();//remove temp file
//			 file.delete();//remove spreadsheet file
//			 is.close();
			 return null;
	}

	@Override
	public Customer getByEmail(String email, Integer storeId) {

		return customerDAO.getByEmail(email, storeId);
	}

	/**
	 * 
	 */
	
	
	@Override
	public void updateCustomerShare(Customer customer) throws ServiceException{
		customerDAO.updateCustomerShare(customer);
		
	}




}
