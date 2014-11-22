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
public class PageWaste implements UnaryFunction<BigDecimal, BigDecimal> {

	public BigDecimal evaluate(BigDecimal pageWast) {
		return pageWast.compareTo(SamplePriceCalculator.MIN_WASTE) == -1 ? SamplePriceCalculator.MIN_WASTE: pageWast; 
	}

}
