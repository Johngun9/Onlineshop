package net.johngun.onlineshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.johngun.online_shopbackend.DAO.CategoryDAO;

@Controller
public class pageController {
	
	@Autowired
	public CategoryDAO categoryDAO;
	
	@RequestMapping(value="/home")
	public ModelAndView home()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","home");
		
		//passing the list of categories
		mv.addObject("categories",categoryDAO.list());
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
	
//	@RequestMapping(value="/show/all/products")
//	public ModelAndView showallproducts()
//	{
//		ModelAndView mv=new ModelAndView("page");
//		mv.addObject("title","all products");
//		 //passig the list of categories
//		
//		mv.addObject("categories","categoryDAO.list()");
//		mv.addObject("userClickAllProducts",true);		
//		return mv;
//	}
//	
//	@RequestMapping(value="/show/category/{id}/products")
//	public ModelAndView showcategoryproducts(@PathVariable("id") int id)
//	{
//		ModelAndView mv=new ModelAndView("page");
//		
//		// getting the single category from categoryDAO
//		mv.addObject("category","categoryDAO.get(id)");
//		
//		mv.addObject("title","all products");
//		
//		 //passig the list of categories		
//		mv.addObject("categories","categoryDAO.list()");
//		mv.addObject("userClickAllProducts",true);		
//		return mv;
//	}
	
	
	
	
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
