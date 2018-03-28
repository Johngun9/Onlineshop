package net.johngun.onlineshop.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException(){
		ModelAndView mv=new ModelAndView("error");
		
		mv.addObject("errorTitle","The page is not contructed");
		mv.addObject("errorDecription","The page you are looking for is not available now!");
		mv.addObject("title","404 Error Page");
		
		return mv;
	}
	
	//Custom Exception
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException(){
		ModelAndView mv=new ModelAndView("error");
		
		mv.addObject("errorTitle","The Product not available");
		mv.addObject("errorDecription","The Product you are looking for is not available right now!");
		mv.addObject("title","Product Unavailable");
		
		return mv;
	}
	
	//Generize Exception
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex){
		ModelAndView mv=new ModelAndView("error");
		
		mv.addObject("errorTitle","Contact your Administrator");
		
		//only for debugging your application purpose 
		StringWriter sw=new StringWriter();
		PrintWriter pw=new PrintWriter(sw);
		ex.printStackTrace(pw);
		mv.addObject("errorDecription",sw.toString());
		mv.addObject("title","Error");
		
		return mv;
	}

}
