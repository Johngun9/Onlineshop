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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.johngun.online_shopbackend.DAO.CategoryDAO;
import net.johngun.online_shopbackend.DAO.ProductDAO;
import net.johngun.online_shopbackend.dto.Category;
import net.johngun.online_shopbackend.dto.Product;
import net.johngun.onlineshop.util.FileUploadUtility;
import net.johngun.onlineshop.validator.ProductValidator;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProduct(@RequestParam(name = "operation", required = false) String operation) {

		ModelAndView mv = new ModelAndView("page");

		mv.addObject("title", "ManageProducts");
		mv.addObject("userClickManageProduct", true);

		Product nproduct = new Product();

		// set few of the fields
		nproduct.setSupplierId(1);
		nproduct.setActive(true);

		mv.addObject("product", nproduct);

		if (operation != null) {

			if (operation.equals("product")) {
				mv.addObject("message", "Product Submitted Successfullly!");
			}
		}

		return mv;
	}

	// Handling product submision into DB
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult br,
			Model model, HttpServletRequest request) 
	{
		// handle validation for new product 
		if(mProduct.getId()==0)
		{
			new ProductValidator().validate(mProduct, br);
		}
		else
		{	if(!(mProduct.getFile().getOriginalFilename().equals("")))
			new ProductValidator().validate(mProduct, br);
		}

		// Check for errors
		if (br.hasErrors()) {

			model.addAttribute("userClickManageProduct", true);
			model.addAttribute("title", "ManageProducts");
			model.addAttribute("message", "Product Submission Failed Jaffa ! ");

			return "page";
		}

		logger.info(mProduct.toString());
		
		if(mProduct.getId()==0)
		{
			//create new product record if id is 0
			productDAO.add(mProduct);
		}
		else
		{
			//update product record if id is not 0
			productDAO.update(mProduct);
		}

		if (!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}

		return "redirect:/manage/products?operation=product";
	}

	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	public ModelAndView showManageProduct(@PathVariable int id) 
	{

		ModelAndView mv = new ModelAndView("page");

		mv.addObject("title", "ManageProducts");
		mv.addObject("userClickManageProduct", true);
		
		//getting product data from database
		Product nproduct = productDAO.get(id);
		
		//setting the product data to the form
		mv.addObject("product", nproduct);
		return mv;

	}

	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		// is going to fetch the product from the database
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		// activating and deactivating based on the value of active field
		product.setActive(!product.isActive());
		// updating the product
		productDAO.update(product);

		return (isActive) ? "You have deactivated the product for the ID :" + product.getId()
				: "You have activated the product for the ID :" + product.getId();
	}

	// returning categories for all request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}
}
