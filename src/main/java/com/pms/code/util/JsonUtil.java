package com.pms.code.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


public class JsonUtil {
	/**
	 * 将对象转化为JSON
	 * @param ob
	 * @return
	 */
	public static String objectToJson(Object ob){
		Gson gson=new Gson();		
		return gson.toJson(ob);
	}
	/**
	 * 输出json
	 * @param response
	 */
	public static void writeJson(HttpServletResponse  response,String  json){
		response.setContentType("text/json;charset=utf-8");
		/*response.setCharacterEncoding("utf-8");*/
		try {
			PrintWriter pw = response.getWriter();
			pw.print(json);
System.out.println(json);
			pw.close();
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*public static void main(String[] args) {
		String str = "{\"list\":[{\"goodId\" : \"商品id1\",\"goodType\": \"商品类型1\"},{\"goodId\" : \"商品id2\",\"goodType\": \"商品类型2\"},{\"goodId\" : \"商品id3\",\"goodType\": \"商品类型3\"}]}";
		id 为购物车的记录主键，count 为购物车对应商品的份数
		JSONObject goods = JSONObject.parseObject(str);
		JSONArray goodsArray = goods.getJSONArray("list");
		List<BusShopCar> bsc = new ArrayList<BusShopCar>();
		for(int i=0;i<goodsArray.size();i++){
			Object good = goodsArray.get(i);
			JSONObject goodJson = JSONObject.parseObject(good.toString());
			Object spId = goodJson.get("spId");
			Object goodType = goodJson.get("goodType");
			BusShopCar busGood = new BusShopCar();
			busGood.setGoodId(spId.toString());
			busGood.setGoodType(goodType.toString());
			
			bsc.add(busGood);
		}
		System.out.println(bsc.toString());
		for(int i=0;i<bsc.size();i++){
			System.out.println(bsc.get(i).getGoodId() + "===" + bsc.get(i).getGoodType());
		}
	}*/
}
