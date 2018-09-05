package com.pms.code.entity.base;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 公告实体类
 * @author Dell
 *
 */
public class SysNotice {
	private int id;
	private String title;//标题
	private String subtitle;//小标题
	private String conStr;//内容
	private String content;//内容
	private String publisher;//发布者
	private int pmid;//管理处ID
	private Timestamp createTime;//创建时间
	private String createtime;//创建时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getConStr() {
		return conStr;
	}
	public void setConStr(String conStr) {
		this.conStr = conStr;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		if(content.length()>20){
			setConStr(content.substring(0, 20)+"...");
		}else{
			setConStr(content);
		}
		this.content = content;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}  
	public int getPmid() {
		return pmid;
	}
	public void setPmid(int pmid) {
		this.pmid = pmid;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime));
		this.createTime = createTime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "SysNotice [id=" + id + ", title=" + title + ", subtitle=" + subtitle + ", content=" + content
				+ ", publisher=" + publisher + ", pmid=" + pmid + ", createTime=" + createTime + ", createtime="
				+ createtime + "]";
	}
}
