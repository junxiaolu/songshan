/*
 * Copyright 2005-2015 laoyasoft inc. All Rights Reserved.
 */
package com.laoyasoft.eas.domain.print;

import java.io.Serializable;

/**
 * 样本报价计算参数类.<br>
 * @author ganmingzhu <br>
 * @version 1.0.0 2014-7-29<br>
 * @see 
 * @since JDK 1.5.0
 */
public class SamplePriceCalcParam implements Serializable {

	/**
	 * A纸张尺寸.
	 */
	private int paperSize;
	
	/**
	 * B页数.
	 */
	private int pages;
	
	/**
	 * C封面纸张.
	 */
	private int coverPaper;
	
	/**
	 * D内页纸张.
	 */
	private int insidePaper;
	
	/**
	 * E数量.
	 */
	private int quantity;
	
	/**
	 * F骑马钉.
	 */
	private boolean isHorseRidingNail;
	
	/**
	 * G封面覆膜.
	 */
	private boolean isCoverLaminate;
	
	/**
	 * H胶装.
	 */
	private boolean isAdhesiveBinding;
	
	/**
	 * J封面UV.
	 */
	private boolean isCoverUV;
	
	/**
	 * K封面烫金.
	 */
	private boolean isCoverBronzing;

	/**
	 * A纸张尺寸
	 * @return A纸张尺寸
	 */
	public int getPaperSize() {
		return paperSize;
	}

	/**
	 * A纸张尺寸
	 * @param newPaperSize
	 */
	public void setPaperSize(final int newPaperSize) {
		paperSize = newPaperSize;
	}

	/**
	 * B页数
	 * @return B页数
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * B页数.
	 * @param newPages B页数
	 */
	public void setPages(final int newPages) {
		pages = newPages;
	}

	/**
	 * C封面纸张.
	 * @return C封面纸张.
	 */
	public int getCoverPaper() {
		return coverPaper;
	}

	/**
	 * C封面纸张.
	 * @param newCoverPaper C封面纸张.
	 */
	public void setCoverPaper(final int newCoverPaper) {
		coverPaper = newCoverPaper;
	}

	/**
	 * D内页纸张.
	 * @return D内页纸张.
	 */
	public int getInsidePaper() {
		return insidePaper;
	}

	/**
	 * D内页纸张.
	 * @param newInsidePaper D内页纸张.
	 */
	public void setInsidePaper(final int newInsidePaper) {
		insidePaper = newInsidePaper;
	}

	/**
	 * E数量.
	 * @return E数量.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * E数量.
	 * @param newQuantity E数量.
	 */
	public void setQuantity(final int newQuantity) {
		quantity = newQuantity;
	}

	/**
	 * F骑马钉
	 * @return F骑马钉
	 */
	public boolean getIsHorseRidingNail() {
		return isHorseRidingNail;
	}

	/**
	 * F骑马钉
	 * @param newIsHorseRidingNail F骑马钉
	 */
	public void setIsHorseRidingNail(final boolean newIsHorseRidingNail) {
		isHorseRidingNail = newIsHorseRidingNail;
	}

	/**
	 * G封面腹膜.
	 * @return G封面腹膜.
	 */
	public boolean getIsCoverLaminate() {
		return isCoverLaminate;
	}
	
	/**
	 * G封面腹膜.
	 * @param newIsCoverPenitone G封面腹膜.
	 */
	public void setIsCoverLaminate(final boolean newIsCoverPenitone) {
		isCoverLaminate = newIsCoverPenitone;
	}

	/**
	 * H胶装.
	 * @return H胶装.
	 */
	public boolean getIsAdhesiveBinding() {
		return isAdhesiveBinding;
	}

	/**
	 * H胶装.
	 * @param newIsAdhesiveBinding H胶装.
	 */
	public void setIsAdhesiveBinding(final boolean newIsAdhesiveBinding) {
		isAdhesiveBinding = newIsAdhesiveBinding;
	}

	/**
	 * J封面UV.
	 * @return J封面UV.
	 */
	public boolean getIsCoverUV() {
		return isCoverUV;
	}

	/**
	 * J封面UV.
	 * @param newIsCoverUV J封面UV.
	 */
	public void setIsCoverUV(final boolean newIsCoverUV) {
		isCoverUV = newIsCoverUV;
	}

	/**
	 * K封面烫金.
	 * @return K封面烫金.
	 */
	public boolean getIsCoverBronzing() {
		return isCoverBronzing;
	}

	/**
	 * K封面烫金.
	 * @param newIsCoverBronzing K封面烫金.
	 */
	public void setIsCoverBronzing(final boolean newIsCoverBronzing) {
		isCoverBronzing = newIsCoverBronzing;
	}
}
