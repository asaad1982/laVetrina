package com.salesmanager.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

public class CustomDateEditorRegistrar extends FormattingConversionServiceFactoryBean {
 
    @Override
    protected void installFormatters(FormatterRegistry registry) {
        super.installFormatters(registry);
        registry.addConverter(getStringToDateConverter());
    }
 
    public Converter<String, Date> getStringToDateConverter() {
        return new Converter<String, Date>() {
 
            @Override
            public Date convert(String source) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return sdf.parse(source);
                } catch (ParseException e) {
                    return null;
                }
            }
        };
    }
}


