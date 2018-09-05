package com.pms.code.util;

/**
 * 业务码ID类型
 */
public enum BizType {

	/** C端线下 */
	ORDER("OCCMZ"),
	/** 社区采购单 */
	SQCG("OCCC");
	
	
	private String nValue;

	private BizType(String value) {
		this.nValue = value;
	};

	public String getValue() {
		return nValue;
	}
}
