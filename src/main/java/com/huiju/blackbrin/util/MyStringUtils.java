package com.huiju.blackbrin.util;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 类说明：
 */
public class MyStringUtils {

    /**
     * 将前端 labelStr 数组转换成字符串
     *
     * @param labelStr 标签数组
     * @return
     */
    public static String labelStrToString(String[] labelStr) {
        return Arrays.toString(labelStr).replace("[", "").replace("]", "");
    }

    /**
     * 将 labelStr 去掉所有空格
     *
     * @param labelStr
     * @return
     */
    public static String[] toArrayWithTrim(String labelStr) {
        if (null==labelStr){
            return null;
        }else {
            return labelStr.replace(" ", "").split(",");
        }
    }
    
    /**
     * 获取Email 前缀
     */
    public static String getPreEmail(String email) {
    	if (null == email){
    		return null;
    	}
    	int ind = email.indexOf("@");
    	if(ind <= 0){
    		return "";
    	}
    	String preStr = email.substring(0, ind);
    	return preStr;
    }
    
    /**
	 * 获取订单号，20位，最多设置30位，格式：AAAAAAyyyyMMddHHmmss
	 * AAAAAA，6位随机数字、字母组合字符串；
	 */
	public static String getOrderNo(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String randomStr = RandomStringUtils.random(6, true, true);
		String date = sdf.format(new Date());
		
		String orderNo = randomStr + date;
		return orderNo;
	}
}
