package com.jackzhang.mall.util;

import java.io.File;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class RandomFileRenamePolicy implements FileRenamePolicy
{
	public File rename(File file) {
		  String body="";
	      String ext="";
	      Date date = new Date();
	      int pot=file.getName().lastIndexOf(".");
	      if(pot!=-1){
	          body= date.getTime() +"";
	          ext=file.getName().substring(pot);
	      }else{
	          body=(new Date()).getTime()+"";
	          ext="";
	      }
	      String newName=body+ext;
	      file=new File(file.getParent(),newName);
	      return file;

		}

}
