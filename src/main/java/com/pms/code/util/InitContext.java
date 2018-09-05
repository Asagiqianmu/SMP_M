package com.pms.code.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @ToDoWhat 
 * @author xmm
 */
public class InitContext implements ApplicationContextAware{
	
	private static ApplicationContext context;
	
	public static void init(){
		context=new ClassPathXmlApplicationContext("classpath*:spring-mvc.xml");
		System.out.println(context.getBean("LoginService"));
	}
	
	static {
		init();
	}

	public static <T> T getBean(String beanName,Class<T> clazz){
		
		return context.getBean(beanName, clazz);
	}

	/**
	 * @author songyb E-mail:songyanbiao@kdflink.com
	 * @version 创建时间：2017年4月13日 下午8:54:48
	 * @describe
	 * @parameter
	 * @return 
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
	}
	
	
	
}
