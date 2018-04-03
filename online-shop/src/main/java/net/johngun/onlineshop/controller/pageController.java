package net.johngun.onlineshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.johngun.online_shopbackend.DAO.CategoryDAO;
import net.johngun.online_shopbackend.DAO.ProductDAO;
import net.johngun.online_shopbackend.dto.Category;
import net.johngun.online_shopbackend.dto.Product;
import net.johngun.onlineshop.exception.ProductNotFoundException;

@Controller
public class pageController {
	
	private static final Logger logger=LoggerFactory.getLogger(pageController.class);
	
	@Autowired
	public CategoryDAO categoryDAO;
	
	@Autowired
	public ProductDAO productDAO;
	
	@RequestMapping(value={"/","/home","/index"})
	public ModelAndView home()
	{
		ModelAndView mv=new ModelAndView("page");
		
		mv.addObject("title","home");
		
		logger.info("Inside PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");
		
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
	
	@RequestMapping(value="/show/all/products")
	public ModelAndView showallproducts()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","Allproducts");
		
		 //passig the list of categories		
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("userClickAllProducts",true);		
		return mv;
	}
	
	@RequestMapping(value="/show/category/{id}/products")
	public ModelAndView showcategoryproducts(@PathVariable("id") int id)
	{
		ModelAndView mv=new ModelAndView("page");
		
		// getting the single category from categoryDAO
		Category category=null;
		category=categoryDAO.get(id);
				
		mv.addObject("title",category.getName());
		
		 //passig the list of categories		
		mv.addObject("categories",categoryDAO.list());
		
//		mv.addObject("products",productDAO.listActiveProductsByCategory(id));
		
		//passing single category
		mv.addObject("category",category);
		
		mv.addObject("userClickCategoryProducts",true);		
		return mv;
	}
	
	@RequestMapping(value="/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException
	{
		ModelAndView mv=new ModelAndView("page");
		Product product =productDAO.get(id);
		
		if(product==null)
		{
			throw new ProductNotFoundException();
		}
		
		//update the view count
		product.setViews(product.getViews()+1);
		productDAO.update(product);
		 
		mv.addObject("title",product.getName());
		
		//passig the list of categories
		mv.addObject("product",product);
		mv.addObject("userClickShowProduct",true);		
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
