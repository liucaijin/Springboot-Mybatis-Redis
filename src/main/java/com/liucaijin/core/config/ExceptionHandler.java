package com.liucaijin.core.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@EnableWebMvc
@ComponentScan(basePackages = {"com.liucaijin"})
@Configuration
public class ExceptionHandler implements org.springframework.web.servlet.HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object paramObject, Exception paramException) {
		
		try {
		request.setCharacterEncoding("UTF-8");
	} catch (UnsupportedEncodingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	response.setContentType("text/html;charset=utf-8");
		 System.out.println("异常消息是：");
	   
 	 PrintWriter writer = null;
	try {
		writer = response.getWriter();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 		 writer.write("异常信息");
		return new ModelAndView();
	}
	
	
	

	 
//	protected ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object arg2,
//			Exception ex) {
//		
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		response.setContentType("text/html;charset=utf-8");
//		String determineViewName = determineViewName(ex, request);
//		System.out.println("determineViewName:"+determineViewName);
//		 determineStatusCode(request, determineViewName);
//		 if(determineViewName != null){
//			 
//		 }else{
//			 try {
//				 String message = ex.getMessage();
//				 System.out.println("异常消息是："+ message);
//				 
//				 PrintWriter writer = response.getWriter();
//				 writer.write(message);
//				 writer.write("     "+message+111111);
//				 writer.flush();
//				 ex=null;
//				 return new  ModelAndView(message); 
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			
//			  
//		 }
//		 return null;
//	}
 
	 
}
