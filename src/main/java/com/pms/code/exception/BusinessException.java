
package com.pms.code.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
* @ClassName: BusinessException 
* @Description: 自定义业务异常
*
 */
public class BusinessException extends RuntimeException{

	private Logger logger = LoggerFactory.getLogger(BusinessException.class);

	private static final long serialVersionUID = 4107999795109334873L;
	
	private String code;
	
	private boolean flage;
	
	private String msg;

	public BusinessException(){
		
	}
	
	public BusinessException(String code,boolean flage,String msg){
		this.code = code;
		this.flage = flage;
		this.msg = msg;
		
		logger.error(this.toString());
	}
	public BusinessException(boolean flage,String msg){
		super();
		this.msg = msg;
		this.flage = flage;
		logger.error(this.toString());
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isFlage() {
		return flage;
	}

	public void setFlage(boolean flage) {
		this.flage = flage;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "BusinessException [code=" + code + ", flage=" + flage
				+ ", msg=" + msg + "]";
	}

	
}
