package net.johngun.onlineshop.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class pageController {
	
	@RequestMapping(value="/home")
	public ModelAndView home()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","home");
		mv.addObject("userClickHome",true);		
		return mv;
	}
	
	
	@RequestMapping(value="/about")
	public ModelAndView about()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","about");
		mv.addObject("userClickabout",true);		
		return mv;
	}
	
	@RequestMapping(value="/contact")
	public ModelAndView contact()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","contact");
		mv.addObject("userClickcontact",true);		
		return mv;
	}
	
	@RequestMapping(value="/productlist")
	public ModelAndView productlist()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","Home");
		mv.addObject("userClickproductlist",true);		
		return mv;
	}
	
	
//	@RequestMapping(value={"/","/test","/productlist","/contact","/about","/home"})
//	public ModelAndView test(@RequestParam(value="greeting", required=false) String greeting)
//	{
//		if(greeting==null){
//			greeting="Hello There is nothing";
//		}
//		ModelAndView mv=new ModelAndView("page");
//		mv.addObject("greetings",greeting);
//		
//		return mv;
//	}
	
	
}
