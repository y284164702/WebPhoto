package com.yuan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.jcp.xml.dsig.internal.MacOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * UUID生成工具
 *
 * @author yuanye
 */
public class UUIDUtils {
	
	/**
	 * 时间信息id
	 * @return
	 */
    public static String getUUID() {
    	String numStr = "";
		String trandStr = String.valueOf((Math.random() * 9 + 1) * 1000000);
		String dataStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		numStr = trandStr.toString().substring(0, 4);
		numStr = dataStr + numStr;
		return numStr;
    }
    
    /**
     * 随机id，32位
     * @return
     */
    public static String getUUID32() {
    	return UUID.randomUUID().toString().replaceAll("-", "");
    }
    

}
