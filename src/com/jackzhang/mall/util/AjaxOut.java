package com.jackzhang.mall.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class AjaxOut 
{
	public static void out(HttpServletResponse response,String str)
	{
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
