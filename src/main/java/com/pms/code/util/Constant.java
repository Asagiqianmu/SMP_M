package com.pms.code.util;

/**
 * API协议常量
 * @author dengfei E-mail:dengfei200857@163.com
 * @time 2017年5月8日 下午2:42:05
 */
public class Constant {
	public final int SN = 0; //sn
	public final int SYSTEM_RUN_TIME = 1; //系统运行时间
	public final int MODEL = 2; //型号
    public final int SYSTEM_VERSION = 3; //系统版本号
    public final int MEMERY = 4; //内存占用比
    public final int CPU = 5; //CPU占用比
    public final int CPU_TEMP = 6; //CPU温度
    public final int DISK_USAGE = 7; //磁盘使用率
    public final int BROADBAND_USAGE = 8; //带宽占用率
    public final int WIRELESS_INFO = 9; //无线信息（射频和用户）
    public final int WAN_PORT_INFO = 10; //wan口
    public final int LAN_PORT_INFO = 11; //lan口
    public final int CONNNECTION_NUM = 12; //连接数
    public final int TYPE = 13; //设备类型
    
    /**
     * zsk使用
     */
	public static final boolean SUCESS_STATE = true;
	public static final boolean ERROR_STATE = false;
	public static final String ERROR_TIP = "系统未知错误";
	
}

