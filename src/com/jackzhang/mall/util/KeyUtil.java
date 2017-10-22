package com.jackzhang.mall.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 自动生成主键帮助类
 * 
 * @author jackzhang
 * 
 */
public class KeyUtil {
	public static String GetKey() {
		Random r = new Random();
		int rint = r.nextInt(100);
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmssS");
		String date = sd.format(new Date());
		return date + rint;
	}
}
