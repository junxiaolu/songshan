/*
 * Copyright 2005-2015 laoyasoft inc. All Rights Reserved.
 */
package com.laoyasoft.eas.controller.print;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.functor.UnaryFunction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.laoyasoft.eas.domain.print.SamplePriceCalcParam;
import com.laoyasoft.eas.util.PriceUtil;

/**
 * 样本报价计算器.<br>
 * 
 * @author ganmingzhu <br>
 * @version 1.0.0 2014-7-29<br>
 * @see
 * @since JDK 1.5.0
 */
@Controller
@RequestMapping("/samplepricecalc")
public class SamplePriceCalculator {

	/**
	 * 计价.
	 * 
	 * @param param
	 *            参数
	 * @return 总价
	 */
	@RequestMapping(value = "/calculate.do", method = RequestMethod.POST)
	public ModelAndView calculate(final SamplePriceCalcParam param) {
		final Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		// 如果页面尺寸、页数或者数量为0，直接返回0
		if (0 == param.getPaperSize() || 0 == param.getQuantity() || 0 == param.getPages()) {
			model.put("price", 0);
		} else {
			final BigDecimal calcPrice1 = calc1(param);
			final BigDecimal calcPrice2 = calc2(param);
			final BigDecimal calcPrice3 = calc3(param);
			final BigDecimal calcPrice4 = calc4(param);
			final BigDecimal calcPrice5 = calc5(param);
			final BigDecimal calcPrice6 = calc6(param);
			final BigDecimal calcPrice7 = calc7(param);
			final BigDecimal calcPrice8 = calc8(param);
			//相加后，最后的输出数为2位小数，四舍五入
			final BigDecimal totalPrice = calcPrice1.add(calcPrice2)
					.add(calcPrice3).add(calcPrice4).add(calcPrice5)
					.add(calcPrice6).add(calcPrice7).add(calcPrice8)
					.setScale(2, RoundingMode.HALF_DOWN);
			model.put("price", totalPrice.doubleValue());
		}
		return new ModelAndView("jsonView", model);
	}

	private final BigDecimal PRICE = PriceUtil.getPrice();
	
	
	private static final BigDecimal getMinWastCover() {
		return null;
	}
	
	public static final BigDecimal MIN_WASTE = new BigDecimal("200");
	
	private UnaryFunction<BigDecimal, BigDecimal> minWaste = new ConverWast();
	/**
	 * 封面价格.
	 * 	 * @param param
	 * @return
	 */
	private BigDecimal calc1(final SamplePriceCalcParam param) {
		// (e/2+200)/4*c*6800/1884/500*1.05
		// e
		final BigDecimal bgQuantity = new BigDecimal(param.getQuantity());
		// c
		final BigDecimal bgCoverPaper = new BigDecimal(param.getCoverPaper());
		// 精度为4，舍入模式为大于0.5进1，否则舍弃
		final MathContext mc = new MathContext(4, RoundingMode.HALF_DOWN);		
		return bgQuantity.divide(new BigDecimal(2), mc)
				.add(minWaste.evaluate(bgQuantity.multiply(new BigDecimal("0.05"))))
				.divide(new BigDecimal(4), mc)
				.multiply(bgCoverPaper)
				.multiply(PRICE)
				.multiply(new BigDecimal(1.05));
	}

	
	private static final UnaryFunction<BigDecimal, BigDecimal> pageWaste = new PageWaste();
	
	/**
	 * . 
	 * @param param
	 * @return
	 */
	private BigDecimal calc2(final SamplePriceCalcParam param) {
		// [(b-4)/4]/2*e/a*d*6800/1884/500*1.05
		// b
		final BigDecimal bgPages = new BigDecimal(param.getPages());
		// e
		final BigDecimal bgQuantity = new BigDecimal(param.getQuantity());
		// a
		final BigDecimal bgPaperSize = new BigDecimal(param.getPaperSize());
		// d
		final BigDecimal bgInsidePaper = new BigDecimal(param.getInsidePaper());
		final MathContext mc = new MathContext(4, RoundingMode.HALF_DOWN);
		final BigDecimal layout = bgPages.subtract(new BigDecimal(4)).divide(new BigDecimal(4), mc).divide(new BigDecimal(2), mc);
		return bgQuantity.add(pageWaste.evaluate(bgQuantity.multiply(new BigDecimal("0.05"))))
			.multiply(layout)
			.divide(new BigDecimal(4), mc)
			.multiply(bgInsidePaper)
			.multiply(PRICE)
			.multiply(new BigDecimal("1.05"));		
		/*// 精度为4，舍入模式为大于0.5进1，否则舍弃		
		return bgPages.subtract(new BigDecimal(4))
				.divide(new BigDecimal(4), mc).divide(new BigDecimal(2), mc)
				.multiply(bgQuantity).divide(bgPaperSize, mc)
				.multiply(bgInsidePaper).multiply(new BigDecimal(6800))
				.divide(new BigDecimal(1884), mc)
				.divide(new BigDecimal(500), mc).multiply(new BigDecimal(1.05));*/
	}

	private static final BigDecimal MIN_PRINT = new BigDecimal("1000");
	private static final BigDecimal MIN_PRINT2 = new BigDecimal("10000");
	/**
	 * .
	 * 
	 * @param param
	 * @return
	 */
	private BigDecimal calc3(final SamplePriceCalcParam param) {
		// b/4*[300+(e*0.02)]
		// b/4必须是整数，如1.5结果按2计算，10.5按11计算
		// b
		final BigDecimal bgPages = new BigDecimal(param.getPages());
		// e
		final BigDecimal bgQuantity = new BigDecimal(param.getQuantity());
		// 精度为1，舍入模式为有小数进1
		final MathContext mc = new MathContext(1, RoundingMode.CEILING);
		final BigDecimal bgPages_4 = bgPages.divide(new BigDecimal(4), mc);
		if(bgQuantity.compareTo(MIN_PRINT) == -1) {
			return bgPages_4.multiply(new BigDecimal("300"));
		} 
		if(bgQuantity.compareTo(MIN_PRINT2) == -1) {
			return bgQuantity.subtract(MIN_PRINT).multiply(new BigDecimal("0.02")).add(new BigDecimal("300")).multiply(bgPages_4);
		}
		return bgPages_4.multiply(bgQuantity).multiply(new BigDecimal("0.05"));
	}

	private static final BigDecimal EMPTY = new BigDecimal(0);
	/**
	 * .
	 * 
	 * @param param
	 * @return
	 */
	private BigDecimal calc4(final SamplePriceCalcParam param) {
		// 如果是骑马钉
		// b*e*0.06
		// 如果小于150按150计算
		BigDecimal bgRet = new BigDecimal(0);
		if (param.getIsHorseRidingNail()) {
			// b
			final BigDecimal bgPages = new BigDecimal(param.getPages());
			// e
			final BigDecimal bgQuantity = new BigDecimal(param.getQuantity());
			bgRet = bgPages.multiply(bgQuantity).multiply(new BigDecimal("0.06"));
		} else {
			return EMPTY;
		}
		if (-1 == bgRet.compareTo(new BigDecimal("150"))) {
			bgRet = new BigDecimal(150);
		}
		return bgRet;
	}

	/**
	 * .
	 * 
	 * @param param
	 * @return
	 */
	private BigDecimal calc5(final SamplePriceCalcParam param) {
		// 如果封面腹膜
		// 0.44*0.59*e*0.8
		// 如果小于150按150计算
		BigDecimal bgRet = new BigDecimal(0);

		if (param.getIsCoverLaminate()) {
			// e
			final BigDecimal bgQuantity = new BigDecimal(param.getQuantity());
			bgRet = bgQuantity.multiply(new BigDecimal("0.44"))
					.multiply(new BigDecimal("0.59"))
					.multiply(new BigDecimal("0.8"));
		} else {
			return EMPTY;
		}
		if (-1 == bgRet.compareTo(new BigDecimal("150"))) {
			bgRet = new BigDecimal("150");
		}
		return bgRet;
	}

	/**
	 * .
	 * 
	 * @param param
	 * @return
	 */
	private BigDecimal calc6(final SamplePriceCalcParam param) {
		// 如果胶装
		// b*e*0.07
		// 如果小于300按300计算
		BigDecimal bgRet = new BigDecimal(0);
		if (param.getIsAdhesiveBinding()) {
			// b
			final BigDecimal bgPages = new BigDecimal(param.getPages());
			// e
			final BigDecimal bgQuantity = new BigDecimal(param.getQuantity());
			bgRet = bgPages.multiply(bgQuantity).multiply(new BigDecimal("0.07"));
		} else {
			return EMPTY;
		}
		if (-1 == bgRet.compareTo(new BigDecimal("300"))) {
			bgRet = new BigDecimal("300");
		}
		return bgRet;
	}

	/**
	 * .
	 * 
	 * @param param
	 * @return
	 */
	private BigDecimal calc7(final SamplePriceCalcParam param) {
		// 如果封面UV
		// e*0.3
		// 如果小于300按300计算
		BigDecimal bgRet = new BigDecimal(0);
		if (param.getIsCoverUV()) {
			// e
			final BigDecimal bgQuantity = new BigDecimal(param.getQuantity());
			bgRet = bgQuantity.multiply(new BigDecimal("0.3"));
		} else {
			return EMPTY;
		}
		if (-1 == bgRet.compareTo(new BigDecimal("300"))) {
			bgRet = new BigDecimal(300);
		}
		return bgRet;
	}

	/**
	 * .
	 * 
	 * @param param
	 * @return
	 */
	private BigDecimal calc8(final SamplePriceCalcParam param) {
		// 如果封面烫金
		// e*0.3
		// 如果小于300按300计算
		BigDecimal bgRet = new BigDecimal(0);
		if (param.getIsCoverBronzing()) {
			// e
			final BigDecimal bgQuantity = new BigDecimal(param.getQuantity());
			bgRet = bgQuantity.multiply(new BigDecimal("0.3"));
		} else {
			return EMPTY;
		}
		if (-1 == bgRet.compareTo(new BigDecimal("300"))) {
			bgRet = new BigDecimal("300");
		}
		return bgRet;
	}
}
