/**
 * 
 */
package com.laoyasoft.eas.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;

/**
 * @author Administrator
 *
 */
public class PriceUtil {
	
	public static BigDecimal getPrice() {
		InputStream inputStream = PriceUtil.class.getClassLoader().getResourceAsStream("Price.properties");
		   Properties p = new Properties();
		   try{
		       p.load(inputStream);
		   } catch (IOException e1){
		    e1.printStackTrace();
		   }
		if (p.containsKey("PRICE")) {
			return new BigDecimal(p.getProperty("PRICE"));
		}
		return new BigDecimal("0.00721868");
	}
	
	public static void main(String[] args) {
		
	}
}
