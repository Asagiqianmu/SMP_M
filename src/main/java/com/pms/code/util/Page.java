/**
 * 
 */
package com.pms.code.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zyl
 * @date 2016年5月26日
 * 
 */
public class Page<T> {
	  private int pageSize=3;            //页大小  
	    private int pageIndex=0;           //当前页号  
	    private int totalPageCount=0;      //总页数  
	    private int record=0;              //记录总数  
	    private List<T> obj=new ArrayList<T>();//对象查询    
	      
	  
	    public Page(int pageSize, int pageIndex) {
			super();
			this.pageSize = pageSize;
			this.pageIndex = pageIndex;
		}

		/**     
	     * @author Mu Xiongxiong        
	     * @created 2017-3-17 下午10:04:41  
	     * @return type  
	     */  
	      
	    public List<T> getHouseList() {  
	        return obj;  
	    }  
	  
	    /**      
	     * @author Mu Xiongxiong       
	     * @created 2017-3-17 下午10:04:41          
	     * @param houseList    
	     */  
	    public void setHouseList(List<T> obj) {  
	        this.obj = obj;  
	    }  
	  
	    //得到开始记录数  
	    public int getSartRow(){  
	        return (pageIndex-1)*pageSize;  
	    }  
	      
	    //得到结束记录数  
	    public int getEndRow(){  
	        return pageSize;  
	    }  
	  
	    public int getPageSize() {  
	        return pageSize;  
	    }  
	  
	    public void setPageSize(int pageSize) {  
	        this.pageSize = pageSize;  
	    }  
	  
	    public int getPageIndex() {  
	        return pageIndex;  
	    }  
	  
	  
	    public int getTotalPageCount() {  
	        return totalPageCount;  
	    }  
	  
	    //总页数  
	    public void setTotalPageCount() {  
	        int totalP = record % getPageSize() == 0 ? record / getPageSize() :  
	            record/ getPageSize() + 1;  
	        this.totalPageCount = totalP;  
	    }  
	  
	    public int getRecord() {  
	        return record;  
	    }  
	      
	    //总记录数  
	    public void setRecord(int record) {  
	        this.record = record;  
	        //设置总页数  
	        setTotalPageCount();  
	    }  
	  
	      
}
