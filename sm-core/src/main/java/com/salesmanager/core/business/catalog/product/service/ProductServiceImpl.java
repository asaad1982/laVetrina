package com.salesmanager.core.business.catalog.product.service;

import java.awt.Graphics2D;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
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
import com.salesmanager.core.business.catalog.category.service.CategoryService;
import com.salesmanager.core.business.catalog.common.CatalogServiceHelper;
import com.salesmanager.core.business.catalog.product.dao.ProductDao;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.ProductCriteria;
import com.salesmanager.core.business.catalog.product.model.ProductList;
import com.salesmanager.core.business.catalog.product.model.SalesReport;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductAttribute;
import com.salesmanager.core.business.catalog.product.model.availability.ProductAvailability;
import com.salesmanager.core.business.catalog.product.model.description.ProductDescription;
import com.salesmanager.core.business.catalog.product.model.image.ProductImage;
import com.salesmanager.core.business.catalog.product.model.manufacturer.ManufacturerDescription;
import com.salesmanager.core.business.catalog.product.model.price.ProductPrice;
import com.salesmanager.core.business.catalog.product.model.relationship.ProductRelationship;
import com.salesmanager.core.business.catalog.product.service.attribute.ProductAttributeService;
import com.salesmanager.core.business.catalog.product.service.availability.ProductAvailabilityService;
import com.salesmanager.core.business.catalog.product.service.image.ProductImageService;
import com.salesmanager.core.business.catalog.product.service.price.ProductPriceService;
import com.salesmanager.core.business.catalog.product.service.relationship.ProductRelationshipService;
import com.salesmanager.core.business.content.model.FileContentType;
import com.salesmanager.core.business.content.model.ImageContentFile;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.order.model.OrderTotal;
import com.salesmanager.core.business.order.model.orderproduct.OrderProduct;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.zone.model.Zone;
import com.salesmanager.core.business.search.service.SearchService;
import com.salesmanager.core.business.tax.model.taxclass.TaxClass;
import com.salesmanager.core.constants.Constants;
import com.salesmanager.core.utils.CoreConfiguration;
import com.salesmanager.core.utils.ProductPriceUtils;
import com.salesmanager.core.utils.ProductUtils;

@Service("productService")
public class ProductServiceImpl extends SalesManagerEntityServiceImpl<Long, Product> implements ProductService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	private final static String PRODUCT_TEMPLATE = "templates/product/Invoice";
	private final static String INVOICE_TEMPLATE_EXTENSION = ".ods";
	private final static String TEMP_INVOICE_SUFFIX_NAME = "_product.ods";
	
	ProductDao productDao;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductAvailabilityService productAvailabilityService;
	
	@Autowired
	ProductPriceService productPriceService;
	
	
	@Autowired
	ProductAttributeService productAttributeService;
	
	@Autowired
	ProductRelationshipService productRelationshipService;
	
	@Autowired
	SearchService searchService;
	
	@Autowired
	ProductImageService productImageService;
	
	@Autowired
	CoreConfiguration configuration;
	
	@Autowired
	private ProductPriceUtils priceUtil;
	@Autowired
	public ProductServiceImpl(ProductDao productDao) {
		super(productDao);
		this.productDao = productDao;
	}

	@Override
	public void addProductDescription(Product product, ProductDescription description)
			throws ServiceException {
		
		
		if(product.getDescriptions()==null) {
			product.setDescriptions(new HashSet<ProductDescription>());
		}
		
		product.getDescriptions().add(description);
		description.setProduct(product);
		update(product);
		searchService.index(product.getMerchantStore(), product);
	}
	
	@Override
	public List<Product> getProducts(List<Long> categoryIds) throws ServiceException {
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Set ids = new HashSet(categoryIds);
		return productDao.getProductsListByCategories(ids);
		
	}
	
	@Override
	public List<Product> getProducts(List<Long> categoryIds, Language language) throws ServiceException {
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Set<Long> ids = new HashSet(categoryIds);
		return productDao.getProductsListByCategories(ids, language);
		
	}
	
	//@Override
/*	public ProductList getProductList(ProductCriteria criteria, List<Long> categoryIds, Language language) throws ServiceException {
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Set<Long> ids = new HashSet(categoryIds);
		return productDao.getProductListByCategories(criteria, ids, language);
		
	}*/

	@Override
	public ProductDescription getProductDescription(Product product, Language language) {
		for (ProductDescription description : product.getDescriptions()) {
			if (description.getLanguage().equals(language)) {
				return description;
			}
		}
		return null;
	}
	
	@Override
	public Product getBySeUrl(MerchantStore store, String seUrl, Locale locale) {
		return productDao.getBySeUrl(store, seUrl, locale);
	}

	@Override
	public Product getProductForLocale(long productId, Language language, Locale locale)
			throws ServiceException {
		Product product =  productDao.getProductForLocale(productId, language, locale);
		

		CatalogServiceHelper.setToAvailability(product, locale);
		CatalogServiceHelper.setToLanguage(product, language.getId());
		return product;
	}

	@Override
	public List<Product> getProductsForLocale(Category category,
			Language language, Locale locale) throws ServiceException {
		
		if(category==null) {
			throw new ServiceException("The category is null");
		}
		
		//Get the category list
		StringBuilder lineage = new StringBuilder().append(category.getLineage()).append(category.getId()).append("/");
		List<Category> categories = categoryService.listByLineage(category.getMerchantStore(),lineage.toString());
		Set<Long> categoryIds = new HashSet<Long>();
		for(Category c : categories) {
			
			categoryIds.add(c.getId());
			
		}
		
		categoryIds.add(category.getId());
		
		//Get products
		List<Product> products = productDao.getProductsForLocale(category.getMerchantStore(), categoryIds, language, locale);
		
		//Filter availability
		
		return products;
	}
	
	@Override
	public ProductList listByStore(MerchantStore store,
			Language language, ProductCriteria criteria) {
		
		return productDao.listByStore(store, language, criteria);
	}
	
	@Override
	public List<Product> listByStore(MerchantStore store) {
		
		return productDao.listByStore(store);
	}
	
	@Override
	public List<Product> listByTaxClass(TaxClass taxClass) {
		return productDao.listByTaxClass(taxClass);
	}
	
	@Override
	public Product getByCode(String productCode, Language language) {
		return productDao.getByCode(productCode, language);
	}
		


	

	@Override
	public void delete(Product product) throws ServiceException {
		LOGGER.debug("Deleting product");
		Validate.notNull(product, "Product cannot be null");
		Validate.notNull(product.getMerchantStore(), "MerchantStore cannot be null in product");
		product = this.getById(product.getId());//Prevents detached entity error
		product.setCategories(null);
		
		Set<ProductImage> images = product.getImages();
		
		for(ProductImage image : images) {
			productImageService.removeProductImage(image);
		}
		
		product.setImages(null);
		
		//related - featured
		List<ProductRelationship> relationships = productRelationshipService.listByProduct(product);
		for(ProductRelationship relationship : relationships) {
			productRelationshipService.delete(relationship);
		}
		
		super.delete(product);
		searchService.deleteIndex(product.getMerchantStore(), product);
		
	}
	
	@Override
	public void create(Product product) throws ServiceException {
		super.create(product);
		searchService.index(product.getMerchantStore(), product);
	}
	

	
	@Override	
	public void saveOrUpdate(Product product) throws ServiceException {

		LOGGER.debug("Save or update product ");
		Validate.notNull(product,"product cannot be null");
		Validate.notNull(product.getAvailabilities(),"product must have at least one availability");
		Validate.notEmpty(product.getAvailabilities(),"product must have at least one availability");

		//List of original availabilities
		Set<ProductAvailability> originalAvailabilities = null;
		
		//List of original attributes
		Set<ProductAttribute> originalAttributes = null;
		
		//List of original reviews
		Set<ProductRelationship> originalRelationships = null;
		
		//List of original images
		Set<ProductImage> originalProductImages = null;
		
		
		if(product.getId()!=null && product.getId()>0) {
			LOGGER.debug("Update product",product.getId());
			//get original product
			Product originalProduct = this.getById(product.getId());
			originalAvailabilities = originalProduct.getAvailabilities();
			originalAttributes = originalProduct.getAttributes();
			originalRelationships = originalProduct.getRelationships();
			originalProductImages = originalProduct.getImages();
		} else {
			
			Set<ProductDescription> productDescriptions = product.getDescriptions();
			product.setDescriptions(null);
			
			super.create(product);
			
			for(ProductDescription productDescription : productDescriptions) {
				addProductDescription(product,productDescription);
			}
		}

		
		LOGGER.debug("Creating availabilities");
		
		//get availabilities
		Set<ProductAvailability> availabilities = product.getAvailabilities();
		List<Long> newAvailabilityIds = new ArrayList<Long>();
		if(availabilities!=null && availabilities.size()>0) {
			for(ProductAvailability availability : availabilities) {
				availability.setProduct(product);
				productAvailabilityService.saveOrUpdate(availability);
				newAvailabilityIds.add(availability.getId());
				//check prices
				Set<ProductPrice> prices = availability.getPrices();
				if(prices!=null && prices.size()>0) {

					for(ProductPrice price : prices) {
						price.setProductAvailability(availability);
						productPriceService.saveOrUpdate(price);
					}
				}	
			}
		}
		
		//cleanup old availability
		if(originalAvailabilities!=null) {
			for(ProductAvailability availability : originalAvailabilities) {
				if(!newAvailabilityIds.contains(availability.getId())) {
					productAvailabilityService.delete(availability);
				}
			}
		}
		
		LOGGER.debug("Creating attributes");
		List<Long> newAttributesIds = new ArrayList<Long>();
		if(product.getAttributes()!=null && product.getAttributes().size()>0) {
			Set<ProductAttribute> attributes = product.getAttributes();
			for(ProductAttribute attribute : attributes) {
				attribute.setProduct(product);
				productAttributeService.saveOrUpdate(attribute);
				newAttributesIds.add(attribute.getId());
			}
		}
		
		//cleanup old attributes
		if(originalAttributes!=null) {
			for(ProductAttribute attribute : originalAttributes) {
				if(!newAttributesIds.contains(attribute.getId())) {
					productAttributeService.delete(attribute);
				}
			}
		}
		
		
		LOGGER.debug("Creating relationships");
		List<Long> newRelationshipIds = new ArrayList<Long>();
		if(product.getRelationships()!=null && product.getRelationships().size()>0) {
			Set<ProductRelationship> relationships = product.getRelationships();
			for(ProductRelationship relationship : relationships) {
				relationship.setProduct(product);
				productRelationshipService.saveOrUpdate(relationship);
				newRelationshipIds.add(relationship.getId());
			}
		}
		//cleanup old relationships
		if(originalRelationships!=null) {
			for(ProductRelationship relationship : originalRelationships) {
				if(!newRelationshipIds.contains(relationship.getId())) {
					productRelationshipService.delete(relationship);
				}
			}
		}
		
		
		LOGGER.debug("Creating images");

		//get images
		List<Long> newImageIds = new ArrayList<Long>();
		Set<ProductImage> images = product.getImages();
		if(images!=null && images.size()>0) {
			for(ProductImage image : images) {
				if(image.getImage()!=null && (image.getId()==null || image.getId()==0L)) {
					image.setProduct(product);
					
			        InputStream inputStream = image.getImage();
			        ImageContentFile cmsContentImage = new ImageContentFile();
			        cmsContentImage.setFileName( image.getProductImage() );
			        cmsContentImage.setFile( inputStream );
			        cmsContentImage.setFileContentType(FileContentType.PRODUCT);
					
					
					productImageService.addProductImage(product, image, cmsContentImage);
					newImageIds.add(image.getId());
				} else {
					productImageService.update(image);
					newImageIds.add(image.getId());
				}
			}
		}
		
		//cleanup old images
		if(originalProductImages!=null) {
			for(ProductImage image : originalProductImages) {
				if(!newImageIds.contains(image.getId())) {
					productImageService.delete(image);
				}
			}
		}
		
		if(product.getId()!=null && product.getId()>0) {
			super.update(product);
		}
		
		searchService.index(product.getMerchantStore(), product);

	}
	
	public List<SalesReport> getProductsSales(long languageId,String start ,String end){
		return productDao.getProductsSales(languageId, start, end);
	}

	@Override
	public ByteArrayOutputStream createProductList(MerchantStore store,List<Product> products, Language language) throws Exception {
		
		

			
			
			
			//get default template
			String template = new StringBuilder().append(PRODUCT_TEMPLATE).append("_").append(language.getCode().toLowerCase()).append(INVOICE_TEMPLATE_EXTENSION).toString();
			
			//try by language
			InputStream is = null;
			try {
				is = getClass().getClassLoader().getResourceAsStream(template);
			} catch (Exception e) {
				LOGGER.warn("Cannot open template " + template);
				throw new Exception("Cannot open " + template);
			}
			
			if(is==null) {
				try {
					is = getClass().getClassLoader().getResourceAsStream(new StringBuilder().append(PRODUCT_TEMPLATE).append(INVOICE_TEMPLATE_EXTENSION).toString());
				} catch (Exception e) {
					LOGGER.warn("Cannot open template " + template);
					throw new Exception("Cannot open " + new StringBuilder().append(PRODUCT_TEMPLATE).append(INVOICE_TEMPLATE_EXTENSION).toString());
				}
			}
			
			if(is==null) {
				LOGGER.warn("Cannot open template " + template);
				throw new Exception("Cannot open " + new StringBuilder().append(PRODUCT_TEMPLATE).append(INVOICE_TEMPLATE_EXTENSION).toString());
			}
			
			File file = new File("Product" + "_working");
			OutputStream os = new FileOutputStream(file);
			IOUtils.copy(is, os);
			os.close();
			//File file = new File(resource.toURI().toURL());
			System.out.println("File:"+file);
			SpreadSheet spreadSheet=SpreadSheet.createFromFile(file);
			Sheet sheet = spreadSheet.getSheet(0);
			
			int i=0;
			sheet.setValueAt("Product Name", 0, 0);
			sheet.setValueAt("Item#", 1, 0);
			sheet.setValueAt("Category", 2, 0);
			sheet.setValueAt("Brand", 3, 0);
			sheet.setValueAt("Price", 4, 0);
			sheet.setValueAt("Stock Quantity", 5, 0);
			for (Product product : products) {
				
			Product product2=getById(product.getId());
			if(product2!=null){
				i++;
			sheet.setValueAt(product2.getProductDescription()!=null?product2.getProductDescription().getName():"", 0, i);
			sheet.setValueAt(product2.getSku(), 1, i);
			for (Iterator iterator = product2.getCategories().iterator(); iterator.hasNext();) {
				Category category = (Category) iterator.next();
				sheet.setValueAt(category.getDescription().getName(), 2, i);
				
			}
			for (Iterator iterator = product2.getManufacturer().getDescriptions().iterator(); iterator.hasNext();) {
				ManufacturerDescription manufacturerDescription = (ManufacturerDescription) iterator.next();
				sheet.setValueAt(manufacturerDescription.getName(), 3, i);
				
			}
			 
				for (Iterator iterator = product2.getAttributes().iterator(); iterator.hasNext();) {
					ProductAttribute productAttribute = (ProductAttribute) iterator.next();
					sheet.setValueAt(productAttribute.getProductAttributePrice(), 4, i);
					
					
				}
				for (Iterator iterator = product2.getAvailabilities().iterator(); iterator.hasNext();) {
					ProductAvailability productAvailability = (ProductAvailability) iterator.next();
					
					sheet.setValueAt(priceUtil.getAdminFormatedAmountWithCurrency(store,productAvailability.defaultPrice().getProductPriceAmount()), 5, i);
					
					
				}
			}
			 
			}
			
			//sheet.getCellAt(0, 0).setImage(arg0)
			//sheet.getCellAt(0, 0).setStyleName(arg0)
			//sheet.getCellAt(0, 0).getStyle().
			
			
			//generate invoice file
			StringBuilder tempProductName = new StringBuilder();
			tempProductName.append("Product").append(new Date()).append(TEMP_INVOICE_SUFFIX_NAME);
			File outputFile = new File(tempProductName.toString());
			OOUtils.open(sheet.getSpreadSheet().saveAs(outputFile));
			
			
			
			final OpenDocument doc = new OpenDocument();
			doc.loadFrom(tempProductName.toString());

			 // Open the PDF document
			 Document document = new Document(PageSize.A4);
			 
			 
			 //File outFile = new File("invoice.pdf");

			 PdfDocument pdf = new PdfDocument();

			 document.addDocListener(pdf);

			 //FileOutputStream fileOutputStream = new FileOutputStream(outFile);
			 ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			 
			 PdfWriter writer = PdfWriter.getInstance(pdf, outputStream);
			 pdf.addWriter(writer);

			 document.open();

			 // Create a template and a Graphics2D object 
			 Rectangle pageSize = document.getPageSize();
			 int w = (int) (pageSize.getWidth() * 0.9);
			 int h = (int) (pageSize.getHeight() * 0.95);
			 PdfContentByte cb = writer.getDirectContent();
			 PdfTemplate tp = cb.createTemplate(w, h);

			 Graphics2D g2 = tp.createPrinterGraphics(w, h, null);
			 // If you want to prevent copy/paste, you can use
			 // g2 = tp.createGraphicsShapes(w, h, true, 0.9f);
			            
			 tp.setWidth(w);
			 tp.setHeight(h);

			 // Configure the renderer
			 ODTRenderer renderer = new ODTRenderer(doc);
			 renderer.setIgnoreMargins(true);
			 renderer.setPaintMaxResolution(true);
			            
			 // Scale the renderer to fit width
			 renderer.setResizeFactor(renderer.getPrintWidth() / w);
			 // Render
			 renderer.paintComponent(g2);
			 g2.dispose();

			 // Add our spreadsheet in the middle of the page
			 float offsetX = (float) ((pageSize.getWidth() - w) / 2);
			 float offsetY = (float) ((pageSize.getHeight() - h) / 2);
			 cb.addTemplate(tp, offsetX, offsetY);
			 // Close the PDF document
			 document.close();
			 outputFile.delete();//remove temp file
			 file.delete();//remove spreadsheet file
			 is.close();
			 return outputStream;
		
		

	}

	@Override
	public List<Object[]> listByStoreGroupByPrices(MerchantStore store) {
		// TODO Auto-generated method stub
		return productDao.listByStoreGroupByPrices(store);
	}

	@Override
	public List<Object[]> listSoldInstoc(MerchantStore store) {
		// TODO Auto-generated method stub
		return productDao.listSoldInstoc(store);
	}

	@Override
	public List<Object[]> customersStatistics(MerchantStore store, String year, String month) {
		// TODO Auto-generated method stub
		return productDao.customersStatistics(store, year, month);
	}
	
	
}
