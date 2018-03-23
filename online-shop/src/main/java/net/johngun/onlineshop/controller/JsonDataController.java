package net.johngun.onlineshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.johngun.online_shopbackend.DAO.ProductDAO;
import net.johngun.online_shopbackend.dto.Product;

@Controller
@RequestMapping("json/data")
public class JsonDataController {
	
	@Autowired
	public ProductDAO productDAO;
	
	@RequestMapping("all/products")
	@ResponseBody
	public List<Product> getAllProducts()
	{
		return productDAO.listActiveProducts();
	}
	
	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> getProductsByCategory(@PathVariable int id)
	{
		return productDAO.listActiveProductsByCategory(id);
	}
	
	
}
