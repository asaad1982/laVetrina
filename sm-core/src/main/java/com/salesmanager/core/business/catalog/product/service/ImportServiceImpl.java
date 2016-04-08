package com.salesmanager.core.business.catalog.product.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.service.CategoryService;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.availability.ProductAvailability;
import com.salesmanager.core.business.catalog.product.model.description.ProductDescription;
import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;
import com.salesmanager.core.business.catalog.product.model.manufacturer.ManufacturerDescription;
import com.salesmanager.core.business.catalog.product.model.price.ProductPrice;
import com.salesmanager.core.business.catalog.product.service.manufacturer.ManufacturerService;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
@Service("importService")
public class ImportServiceImpl implements ImportService {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ManufacturerService manufacturerService;
	@Autowired
	ProductService productService;
	@Override
	public void importFile(FileBean fileBean,MerchantStore store,Language language) throws ServiceException {
		 ByteArrayInputStream bis = new ByteArrayInputStream(fileBean.getFileData().getBytes());
	        Workbook workbook;
	        try {
	            if (fileBean.getFileData().getOriginalFilename().endsWith("xls")) {
	                workbook = new HSSFWorkbook(bis);
	            } else if (fileBean.getFileData().getOriginalFilename().endsWith("xlsx")) {
	                workbook = new XSSFWorkbook(bis);
	            } else {
	                throw new ServiceException("upload.error.message");
	            }
	            List<Manufacturer> manufacturers=manufacturerService.listByStore(store, language);
	            Sheet sheet=workbook.getSheetAt(0);
	            List<String> errors=new ArrayList<String>();
	            List<String> thrownError=new ArrayList<String>();
	            for (Row row : sheet) {
	            	Product product=new Product();
	            	product.setMerchantStore(store);
	            	if(row.getRowNum()!=0){
	            	if(containsValue(row,0,8)){
	            	
	                  Iterator<Cell> cellIterator = row.cellIterator();
	                  product.setDescriptions(null); 
	                  ProductDescription description=new ProductDescription();
	                  description.setLanguage(language);
	                  ProductAvailability productAvailability =new ProductAvailability();
	                  while (cellIterator.hasNext()) {
	                      Cell cell = cellIterator.next();
	                      
	                     // DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
	                      String cellVal=cellToString(cell);
	                      if(cell.getCellType() != Cell.CELL_TYPE_BLANK){
	                      if(cell.getColumnIndex()==0){
	                    	  List< Category> categories=categoryService.getByName(store, cellVal, language) ;
	                    	  product.setCategories(new HashSet<Category>(categories));
	                      }else if(cell.getColumnIndex()==1){
	                    	 for(Manufacturer manufacturer:manufacturers){
	                    		 for (Iterator iterator = manufacturer.getDescriptions().iterator(); iterator.hasNext();) {
	                    			 ManufacturerDescription manufacturerDescription  = (ManufacturerDescription) iterator.next();
	                    			 if(manufacturerDescription.getName().equalsIgnoreCase(cell.getStringCellValue())){
	                    				 product.setManufacturer(manufacturer);
	                    			 }
	                    		 }
	                    	 }
								
							}else if(cell.getColumnIndex()==2){	
								
								description.setName(cellVal);
								
										
							}else if(cell.getColumnIndex()==3){						
								description.setDescription(cellVal);								
							}
							else if(cell.getColumnIndex()==4){
								try{
								
								productAvailability.setProductQuantity((int) Double.parseDouble(cellVal) );
								}catch(NumberFormatException numberFormatException){
									errors.add("Product Quantity is number only on row"+row.getRowNum());
								}
	                    	  
	                      }else if(cell.getColumnIndex()==6){
								
								productAvailability.setPrices(new HashSet<ProductPrice>());
								ProductPrice productPrice=new ProductPrice();
								try{
								productPrice.setProductPriceAmount(new BigDecimal(Double.parseDouble(cellVal)));
								}catch(NumberFormatException nfe){
	                    			errors.add("Product Price Amount is number only on row"+row.getRowNum());
	                    		}
								productAvailability.getPrices().add(productPrice);
	                    	  
	                      }else if(cell.getColumnIndex()==5){
	                    	  product.setSku(cellVal);
	                      }else if(cell.getColumnIndex()==7){
	                    		try{									
	                    			product.setProductWeight(new BigDecimal(Double.parseDouble(cellVal)));
	                    		}catch(NumberFormatException nfe){
	                    			errors.add("Product Weight is number only on row"+row.getRowNum());
	                    		}
	                      }else  if(cell.getColumnIndex()==8){
	                    	  boolean b =false;
	                    	  try{
	                    	  int i= Integer.parseInt(cellVal);
	                    	   b = (i != 0);
	                    	  }catch (Exception e){
	                    		  e.printStackTrace();
	                    		  try{
	                    		  b=cell.getBooleanCellValue();
	                    		  }catch(Exception ex){
	                    			  errors.add("Invalid Status for product on row"+row.getRowNum());
	                    		  }
	                    	  }
	                    	  
	                    	  product.setAvailable(b);
	                      }
	                  }
	                  }
	                  if(errors.size()==0){
	                 
		                  productService.save(product);
		                  product.setDescriptions(new HashSet<ProductDescription>());
		                  product.getDescriptions().add(description);
		                  description.setProduct(product);
		                  product.setAvailabilities(new HashSet<ProductAvailability>());
		                  product.getAvailabilities().add(productAvailability);
		                  productAvailability.setProduct(product);
		                  
		                  productService.update(product);
	                  }
	                  thrownError.addAll(errors);
	                  errors=Collections.emptyList();
	            	}
	            
	            }
	            }
                if(thrownError.size()>0){
                	throw new ServiceException(thrownError);
                }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}
	public boolean containsValue(Row row, int fcell, int lcell) 
	{
	    boolean flag = false;
	    for (int i = fcell; i < lcell; i++) {
	    if (StringUtils.isEmpty(String.valueOf(row.getCell(i))) == true || 
	        StringUtils.isWhitespace(String.valueOf(row.getCell(i))) == true || 
	        StringUtils.isBlank(String.valueOf(row.getCell(i))) == true || 
	        String.valueOf(row.getCell(i)).length() == 0 || 
	        row.getCell(i) == null) {} 
	    else {
	                flag = true;
	        }
	    }
	        return flag;
	}
	private  String cellToString(Cell cell) throws ServiceException{  
	    int type;
	    Object result=null;
	    type = cell.getCellType();

	    switch (type) {

	        case Cell.CELL_TYPE_NUMERIC: // numeric value in Excel
	        case Cell.CELL_TYPE_FORMULA: // precomputed value based on formula
	            result = cell.getNumericCellValue();
	            break;
	        case Cell.CELL_TYPE_STRING: // String Value in Excel 
	            result = cell.getStringCellValue();
	            break;
	        case Cell.CELL_TYPE_BLANK:
	            result = "";
	        case Cell.CELL_TYPE_BOOLEAN: //boolean value 
	            result: cell.getBooleanCellValue();
	            break;
	        case Cell.CELL_TYPE_ERROR:
	        default:  
	            throw new ServiceException("There is no support for this type of cell");                        
	    }
	    if(result!=null)
	    return result.toString();
	    else
	    return "";
	}

}
