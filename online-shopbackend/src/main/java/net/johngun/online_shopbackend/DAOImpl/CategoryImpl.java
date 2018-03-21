package net.johngun.online_shopbackend.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.johngun.online_shopbackend.DAO.CategoryDAO;
import net.johngun.online_shopbackend.dto.Category;

@Repository("categoryDAO")
public class CategoryImpl implements CategoryDAO {
	
	private static List<Category> categories=new ArrayList<Category>();
	
	static
	{
		Category category =new Category();
		category.setId(1);
		category.setName("TV");
		category.setDescription("This is Apple TV");
		category.setImageURL("CAT_1.png");
		
		categories.add(category);
		
		//second Category
		 category =new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("This is Apple Mobile");
		category.setImageURL("CAT_2.png");
		
		categories.add(category);
		
		//third category
		 category =new Category();
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("This is Apple Laptop");
		category.setImageURL("CAT_3.png");
		
		categories.add(category);
		}
	
	@Override
	public List<Category> list() {
		
		
		return categories;
	}

	@Override
	public Category getCategory(int id) {
		
		return null;
	}

}
