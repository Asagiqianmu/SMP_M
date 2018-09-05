package com.pms.code.entity.base;

/**
 * 通知支付返回信息实体类
 * @author Dell
 *
 */
public class NotifyPaymentResult {
	private int id;
	private String appid;//微信分配的小程序ID
	private String mch_id;//微信支付分配的商户号
	private String device_info;//设备号，小程序传"WEB"
	private String nonce_str;//随机字符串，不长于32位
	private String sign;//签名
	private String sign_type;//签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
	private String result_code;//SUCCESS/FAIL
	private String err_code;//错误返回的信息描述
	private String err_code_des;//错误返回的信息描述
	private String openid;//用户在商户appid下的唯一标识
	private String is_subscribe;//用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
	private String trade_type;//JSAPI、NATIVE、APP
	private String bank_type;//银行类型，采用字符串类型的银行标识
	private int total_fee;//订单总金额，单位为分
	private int settlement_total_fee;//应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额
	private String fee_type;//货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY
	private int cash_fee;//现金支付金额订单现金支付金额
	private String cash_fee_type;//货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY
	private int coupon_fee;//代金券金额<=订单金额，订单金额-代金券金额=现金支付金额
	private int coupon_count;//代金券使用数量
	private int coupon_type_$n;//CASH--充值代金券NO_CASH---非充值代金券，仅在使用了免充值代金券时有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_0
	private String coupon_id_$n;//代金券ID,$n为下标，从0开始编号
	private int coupon_fee_$n;//单个代金券支付金额,$n为下标，从0开始编号
	private String transaction_id;//微信支付订单号
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
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getIs_subscribe() {
		return is_subscribe;
	}
	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public int getSettlement_total_fee() {
		return settlement_total_fee;
	}
	public void setSettlement_total_fee(int settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public int getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee(int cash_fee) {
		this.cash_fee = cash_fee;
	}
	public String getCash_fee_type() {
		return cash_fee_type;
	}
	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}
	public int getCoupon_fee() {
		return coupon_fee;
	}
	public void setCoupon_fee(int coupon_fee) {
		this.coupon_fee = coupon_fee;
	}
	public int getCoupon_count() {
		return coupon_count;
	}
	public void setCoupon_count(int coupon_count) {
		this.coupon_count = coupon_count;
	}
	public int getCoupon_type_$n() {
		return coupon_type_$n;
	}
	public void setCoupon_type_$n(int coupon_type_$n) {
		this.coupon_type_$n = coupon_type_$n;
	}
	public String getCoupon_id_$n() {
		return coupon_id_$n;
	}
	public void setCoupon_id_$n(String coupon_id_$n) {
		this.coupon_id_$n = coupon_id_$n;
	}
	public int getCoupon_fee_$n() {
		return coupon_fee_$n;
	}
	public void setCoupon_fee_$n(int coupon_fee_$n) {
		this.coupon_fee_$n = coupon_fee_$n;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	@Override
	public String toString() {
		return "NotifyPaymentResult [id=" + id + ", appid=" + appid + ", mch_id=" + mch_id + ", device_info="
				+ device_info + ", nonce_str=" + nonce_str + ", sign=" + sign + ", sign_type=" + sign_type
				+ ", result_code=" + result_code + ", err_code=" + err_code + ", err_code_des=" + err_code_des
				+ ", openid=" + openid + ", is_subscribe=" + is_subscribe + ", trade_type=" + trade_type
				+ ", bank_type=" + bank_type + ", total_fee=" + total_fee + ", settlement_total_fee="
				+ settlement_total_fee + ", fee_type=" + fee_type + ", cash_fee=" + cash_fee + ", cash_fee_type="
				+ cash_fee_type + ", coupon_fee=" + coupon_fee + ", coupon_count=" + coupon_count + ", coupon_type_$n="
				+ coupon_type_$n + ", coupon_id_$n=" + coupon_id_$n + ", coupon_fee_$n=" + coupon_fee_$n
				+ ", transaction_id=" + transaction_id + "]";
	}
	
	
}
