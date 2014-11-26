package com.me.spring.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Simple {

	@RequestMapping("/simple.do")
	public String simple(HttpServletRequest req, @RequestParam(required=false, value="start") Date start, @RequestParam(value="str")String str){
		Enumeration<String> attrNames = req.getAttributeNames();
		while(attrNames.hasMoreElements()){
			String attrName = attrNames.nextElement();
			Object attrVal = req.getAttribute(attrName);
			System.out.println(attrName + ":" + attrVal);
		}
		System.out.println(start.toString());
		System.out.println(str);
		return "index";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		
		//�������ַ����Զ�ת��ΪDate
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(format, true));
		
		//�ַ����Զ�ȥ�ո�
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		
		//�Զ���String���ݰ�
		/*binder.registerCustomEditor(String.class, new PropertyEditorSupport(){
			
			public void setAsText(String text){
				setValue(text+":binder");
			}
			
		});*/
	}
}
