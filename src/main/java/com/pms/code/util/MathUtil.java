package com.pms.code.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtil {
	
	public static double add(double d1, double d2) { // 进行加法运算
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.add(b2).doubleValue();
	}

	public static double sub(double d1, double d2) { // 进行减法运算
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.subtract(b2).doubleValue();
	}

	public static double mul(double d1, double d2) { // 进行乘法运算
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.multiply(b2).doubleValue();
	}
	
    public static double div(double d1,double d2) { // 进行除法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2,2,RoundingMode.HALF_UP).doubleValue();
    }
    public static double myround(double num)//double 四舍五入取整
    {
      BigDecimal b=new BigDecimal(num);
      num=b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
      return num;
    }
}
