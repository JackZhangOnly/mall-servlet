package com.jackzhang.mall.util;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtil {
	
	/**
	 * ��ʽ����ǰʱ��
	 * @param date
	 * @return
	 */
	public static String getDate()
	{
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sd.format(new Date());
	}
}
