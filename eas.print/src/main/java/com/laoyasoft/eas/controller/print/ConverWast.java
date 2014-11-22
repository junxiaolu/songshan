/**
 * 
 */
package com.laoyasoft.eas.controller.print;

import java.math.BigDecimal;

import org.apache.commons.functor.UnaryFunction;

/**
 * @author Administrator
 *
 */
public class ConverWast implements UnaryFunction<BigDecimal, BigDecimal> {
	
	
	public BigDecimal evaluate(BigDecimal wastQuantity) {
		return SamplePriceCalculator.MIN_WASTE.compareTo(wastQuantity) == -1 ? wastQuantity : SamplePriceCalculator.MIN_WASTE;
	}

}
