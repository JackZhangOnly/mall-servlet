package com.jackzhang.mall.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码
 * @author hpj
 *
 */
public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = 7351900081378246346L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method != null) {
			if ("randletters".equals(method)) {
				randLetters(request, response);
			}
		}
	}
	
	public void randLetters(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException { 
        response.setContentType("image/jpeg"); //必须设置ContentType为image/jpeg   
        response.setHeader("Pragma","No-cache");    
        response.setHeader("Cache-Control","no-cache");    
        response.setDateHeader("Expires",0);    
        //设置图片的长宽  验证码长度   
        int width=130, height=30,len=4;   
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";   
        int length = base.length();   
        //创建内存图像    
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);    
        //获取图形上下文    
        Graphics g = image.getGraphics();   
        //创建随机类的实例    
        Random random = new Random();   
        //设定图像背景色(因为是做背景，所以偏淡)    
        //g.setColor(getRandColor(random,200,250)); 
        g.setColor(new Color(255,255,255)); 
        
        g.fillRect(0, 0, width, height);   
        //备选字体    
        String[] fontTypes = {"Harrington","Viner Hand ITC"};    
        int fontTypesLength = fontTypes.length;    
        //在图片背景上增加噪点    
        //g.setColor(getRandColor(random,160,200));    
        g.setFont(new Font("Times New Roman",Font.PLAIN,30));    
        for (int i=0;i<6;i++)    
        {    
          g.drawString("!@#$%^,.;'[javawind.net]/<&*()>:5277",0,5*(i+2));    
        }   
           
        String sRand="",pStr="";    
        for (int i=0;i<len;i++)    
        {    
          int start = random.nextInt(length);    
          String rand=base.substring(start,start+1);    
          sRand+=rand;           
          //设置字体的颜色    
          g.setColor(getRandColor(random,10,150));    
          //设置字体    
          g.setFont(new Font(fontTypes[random.nextInt(fontTypesLength)],Font.BOLD,18));    
          //将随机验证码画到图片上    
          //g.drawString(rand,15*i,18);   
          pStr = sRand.substring(i,i+1);  
          g.drawString(pStr,i*30+4,18);
        }   
            
        //将认证码存入session    
        sRand=sRand.toLowerCase(); 
        request.getSession().setAttribute("SystemCode",sRand);   
           
        g.dispose();   
        //输出图象到页面    
        ImageIO.write(image,"JPEG",response.getOutputStream());   
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	/**
	 * 随机颜色
	 * @param random
	 * @param fc
	 * @param bc
	 * @return
	 */
    protected Color getRandColor(Random random,int fc,int bc)   
    {    
        if(fc>255) fc=255;    
        if(bc>255) bc=255;    
        int r=fc+random.nextInt(bc-fc);    
        int g=fc+random.nextInt(bc-fc);    
        int b=fc+random.nextInt(bc-fc);    
        return new Color(r,g,b);    
    }  
}
