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
 * ��֤��
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
        response.setContentType("image/jpeg"); //��������ContentTypeΪimage/jpeg   
        response.setHeader("Pragma","No-cache");    
        response.setHeader("Cache-Control","no-cache");    
        response.setDateHeader("Expires",0);    
        //����ͼƬ�ĳ���  ��֤�볤��   
        int width=130, height=30,len=4;   
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";   
        int length = base.length();   
        //�����ڴ�ͼ��    
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);    
        //��ȡͼ��������    
        Graphics g = image.getGraphics();   
        //����������ʵ��    
        Random random = new Random();   
        //�趨ͼ�񱳾�ɫ(��Ϊ��������������ƫ��)    
        //g.setColor(getRandColor(random,200,250)); 
        g.setColor(new Color(255,255,255)); 
        
        g.fillRect(0, 0, width, height);   
        //��ѡ����    
        String[] fontTypes = {"Harrington","Viner Hand ITC"};    
        int fontTypesLength = fontTypes.length;    
        //��ͼƬ�������������    
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
          //�����������ɫ    
          g.setColor(getRandColor(random,10,150));    
          //��������    
          g.setFont(new Font(fontTypes[random.nextInt(fontTypesLength)],Font.BOLD,18));    
          //�������֤�뻭��ͼƬ��    
          //g.drawString(rand,15*i,18);   
          pStr = sRand.substring(i,i+1);  
          g.drawString(pStr,i*30+4,18);
        }   
            
        //����֤�����session    
        sRand=sRand.toLowerCase(); 
        request.getSession().setAttribute("SystemCode",sRand);   
           
        g.dispose();   
        //���ͼ��ҳ��    
        ImageIO.write(image,"JPEG",response.getOutputStream());   
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	/**
	 * �����ɫ
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
