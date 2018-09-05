package com.pms.code.entity.base;

import java.sql.Timestamp;

/*
 * 统一支付订单实体类
 */
public class PayUnifiedOrder {
	private int id;
	private String appid;//微信分配的小程序ID
	private String mch_id;//微信支付分配的商户号
	private String device_info;//设备号，小程序传"WEB"
	private String nonce_str;//随机字符串，不长于32位
	private String sign;//签名
	private String sign_type;//签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
	private String body;//商品简单描述，该字段须严格按照规范传递
	private String detail;//单品优惠字段
	private String attach;//附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
	private String out_trade_no;//商户系统内部的订单号,32个字符内、可包含字母, 其他说明见
	private String fee_type;//符合ISO 4217标准的三位字母代码，默认人民币：CNY
	private int total_fee;//订单总金额，单位为分
	private String spbill_create_ip;//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
	private String time_start;//订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
	private String time_expire;//订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010
	private String goods_tag;//商品标记，代金券或立减优惠功能的参数
	private String notify_url;//接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
	private String trade_type;//小程序取值如下：JSAPI
	private String limit_pay;//no_credit--指定不能使用信用卡支付
	private String openid;//trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识
	private Timestamp createtime;//创建时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public String getTime_expire() {
		return time_expire;
	}
	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}
	public String getGoods_tag() {
		return goods_tag;
	}
	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getLimit_pay() {
		return limit_pay;
	}
	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "PayUnifiedOrder [id=" + id + ", appid=" + appid + ", mch_id=" + mch_id + ", device_info=" + device_info
				+ ", nonce_str=" + nonce_str + ", sign=" + sign + ", sign_type=" + sign_type + ", body=" + body
				+ ", detail=" + detail + ", attach=" + attach + ", out_trade_no=" + out_trade_no + ", fee_type="
				+ fee_type + ", total_fee=" + total_fee + ", spbill_create_ip=" + spbill_create_ip + ", time_start="
				+ time_start + ", time_expire=" + time_expire + ", goods_tag=" + goods_tag + ", notify_url="
				+ notify_url + ", trade_type=" + trade_type + ", limit_pay=" + limit_pay + ", openid=" + openid
				+ ", createtime=" + createtime + "]";
	}
}
