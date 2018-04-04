package net.johngun.onlineshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.johngun.online_shopbackend.DAO.CategoryDAO;
import net.johngun.online_shopbackend.DAO.ProductDAO;
import net.johngun.online_shopbackend.dto.Category;
import net.johngun.online_shopbackend.dto.Product;
import net.johngun.onlineshop.util.FileUploadUtility;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	private static final Logger logger=LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showManageProduct(@RequestParam(name="operation", required=false) String operation){
		
		ModelAndView mv=new ModelAndView("page");
		
		mv.addObject("title","ManageProducts");
		mv.addObject("userClickManageProduct", true);
		
		Product nproduct=new Product();
		
		//set few of the fields
		nproduct.setSupplierId(1);
		nproduct.setActive(true);
		
		mv.addObject("product",nproduct);
		
		if(operation!=null){
			
			if(operation.equals("product")){
				mv.addObject("message","Product Submitted Successfullly!");
			}
		}
		
		return mv;
		
	}
	
	//Handling product submision into DB
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult br , Model model,
			HttpServletRequest request)
	{
		//Check for errors
		if(br.hasErrors()){
			
			model.addAttribute("userClickManageProduct", true);
			model.addAttribute("title", "ManageProducts");
			model.addAttribute("message", "Product Submission Failed Jaffa ! ");
			
			return "page";
		}
		
		logger.info(mProduct.toString());
		
		productDAO.add(mProduct);
		
		if(!mProduct.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}
	
	//returning categories for all request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories()
	{
		return categoryDAO.list();
	}
}
