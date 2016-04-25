package com.salesmanager.core.business.sale.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesReader extends Properties {

	private static final long serialVersionUID = 6929727398518181570L;
	private static Map cache = new HashMap();

	private String fileName;
	public static final String SERVICE_PROPS = "service.properties";
	/**
	 * Private to disallow instanciation, use getInstance instead
	 * 
	 * @param fileName Name of the properties file, must be in the classpath
	 * @throws IOException
	 */
	private PropertiesReader(String fileName) throws IOException {
		this.fileName = fileName;
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		if (in == null) {
			throw new IOException("File not found: " + fileName);
		}
		super.load(in);
	}

	/**
	 * Gets a PropertiesReader instance for the given fileName
	 * 
	 * @param fileName must be in the classpath
	 * @return The PropertiesReader for the given properties file
	 */
	public static synchronized PropertiesReader getInstance(String fileName) {
		try {
			PropertiesReader instance = (PropertiesReader) cache.get(fileName);
			instance = (PropertiesReader) cache.get(fileName);
			if (instance == null) {
				// create and store
				instance = new PropertiesReader(fileName);
				cache.put(fileName, instance);
			}
			return instance;
		} catch (Exception e) {
			// this is a deployment or programming error, so we use unchecked exception
			throw new RuntimeException("Unable to load properties file " + fileName, e);
		}
	}

	/**
	 * This method may not be used with PropertiesReader since this may change the source of the properties, use reload
	 * to reload properties instead
	 * 
	 * @param in InputStream
	 */
	public void load(InputStream in) {
		throw new RuntimeException("Use getInstance and reload instead!");
	}

	/**
	 * Reloads the Properties from the same properties file
	 * 
	 * @throws IOException
	 */
	public void reload() throws IOException {
		super.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
	}

}
