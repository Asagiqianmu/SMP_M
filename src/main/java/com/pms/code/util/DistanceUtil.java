package com.pms.code.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class DistanceUtil {
	private static final double EARTH_RADIUS = 6378137;//地球半径，单位是：米
	
	private static double rad(double d){
       return d * Math.PI / 180.0;
    }
    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     * @param lng1 经度1 
     * @param lat1 纬度1
     * @param lng2 经度2 
     * @param lat2 纬度2
     * @return 
     */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2){
       double radLat1 = rad(lat1);
       double radLat2 = rad(lat2);
       double a = radLat1 - radLat2;
       double b = rad(lng1) - rad(lng2);
       double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) 
    		   + Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
       s = s * EARTH_RADIUS;
       s = Math.round(s * 10000) / 10000;
       return s;
    }

//    public static final String KEY_1 = "7d9fbeb43e975cd1e9477a7e5d5e192a";// 百度地图key  
    
    public static final String KEY_1 = "b70ca1e8fd1aa909c0f9f8b5c1831501";// 高德地图key
    
    /** 
     * 返回输入地址的经纬度坐标 
     * key lng(经度),lat(纬度) 
     */  
    public static Map<String,String> getGeocoderLatitude(String address){  
        BufferedReader in = null;  
        try {  
            //将地址转换成utf-8的16进制  
            address = URLEncoder.encode(address, "UTF-8");  
            URL tirc = new URL("http://restapi.amap.com/v3/geocode/geo?address="+ address +"&output=json&key="+ KEY_1);  
              
            in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));  
            String res;  
            StringBuilder sb = new StringBuilder("");  
            while((res = in.readLine())!=null){  
                sb.append(res.trim());  
            }  
            String str = sb.toString();  
            Map<String,String> map = null;  
            if(str != null){  
                /* 百度地图拿经纬度
                int lngStart = str.indexOf("lng\":");  
                int lngEnd = str.indexOf(",\"lat");  
                int latEnd = str.indexOf("},\"precise");  
                if(lngStart > 0 && lngEnd > 0 && latEnd > 0){  
                    String lng = str.substring(lngStart+5, lngEnd);  
                    String lat = str.substring(lngEnd+7, latEnd);  
                    map = new HashMap<String,String>();  
                    map.put("lng", lng);  
                    map.put("lat", lat);  
        	       	System.out.println("经度："+lng+"---纬度："+lat);
                    return map;  
                }*/ 
//            	int lngStart = str.indexOf("location\":");  
//            	int lngEnd = str.indexOf(",\"lat");  
//            	int latEnd = str.indexOf("},\"precise");  
            	int start = str.indexOf("location\":"); 
            	int end = str.indexOf(",\"level\":"); 
				if (start > 0 && end > 0) {
					String location = str.substring(start + 11, end - 1);
					String[] array = location.split(",");
					System.out.println(location + "====");
					String lng = array[0];
					String lat = array[1];
					map = new HashMap<String, String>();
					map.put("lng", lng);
					map.put("lat", lat);
					System.out.println("经度：" + lng + "---纬度：" + lat);
					return map;
				}
            }  
        }catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }  
	/** 
     * 生成以中心点为中心的四边经纬度 
     *  
     * @param lat 纬度 
     * @param lon 精度 
     * @param raidus 半径（以米为单位） 
     * @return 
     */   
    public static double[] getAround(double lat, double lon, int raidus) {   
    	//纬度
        Double latitude = lat;   
        //经度
        Double longitude = lon;   
        
        Double degree = (24901 * 1609) / 360.0;   
        double raidusMile = raidus;   
        
        Double dpmLat = 1 / degree;   
        Double radiusLat = dpmLat * raidusMile; 
        //最小纬度
        Double minLat = latitude - radiusLat;   
        //最大纬度
        Double maxLat = latitude + radiusLat;   
   
        Double mpdLng = degree * Math.cos(latitude * (Math.PI / 180));   
        Double dpmLng = 1 / mpdLng;                
        Double radiusLng = dpmLng * raidusMile;
        //最小经度
        Double minLng = longitude - radiusLng;     
        //最大经度
        Double maxLng = longitude + radiusLng;     
        //System.out.println("["+minLat+","+minLng+","+maxLat+","+maxLng+"]");
        return new double[] { minLat, minLng, maxLat, maxLng };
    }
    public static void main(String args[]){  
    	//System.out.println(getDistance(109.000662, 34.25091, 109.001026, 34.250862)/1000);
    	System.out.println(DistanceUtil.getGeocoderLatitude("陕西省西安市雁塔区高新六路北口"));
    	//getAround(Double.parseDouble("34.250557"),Double.parseDouble("109.001176"),15000);
    }  
}
