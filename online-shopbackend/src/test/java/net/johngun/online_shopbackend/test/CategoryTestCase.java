package net.johngun.online_shopbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.johngun.online_shopbackend.DAO.CategoryDAO;
import net.johngun.online_shopbackend.dto.Category;

public class CategoryTestCase {
	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;
	
	@BeforeClass
	public static void init(){
		context =new AnnotationConfigApplicationContext();
		context.scan("net.johngun.online_shopbackend");
		context.refresh();
		categoryDAO=(CategoryDAO)context.getBean("categoryDAO");		
	}
	
//	@Test
//	public void testAddCategory(){
//		category=new Category();
//		
//		category.setName("Mobile");
//		category.setDescription("This is Apple Mobile");
//		category.setImageURL("CAT_3.png");
//		
//		assertEquals("Successfully added a category inside the table!",true,categoryDAO.add(category));
//	}
	
//	@Test
//	public void testgetCategory()
//	{
//		category=categoryDAO.get(3);
//		assertEquals("Successfully get a category inside the table!","Mobile",category.getName());
//	}
	
//	@Test
//	public void testupdateCategory()
//	{
//		category=categoryDAO.get(3);
//		
//		category.setName("Blacberry");
//		assertEquals("Successfully updated a category inside the table!",true,categoryDAO.update(category));
//	}
	
//	@Test
//	public void testdelteCategory()
//	{
//		category=categoryDAO.get(3);		
//		
//		assertEquals("Successfully delete a category inside the table!",true,categoryDAO.delete(category));
//	}
	
//	@Test
//	public void testlistCategory()
//	{		
//		assertEquals("Successfully fetch the list of categories from the table!",3,categoryDAO.list().size());
//	}
	
	@Test
	public void testCRUDCategory(){
		category=new Category();
		
		//Addint category
		category.setName("Mobile");
		category.setDescription("This is Apple Mobile");
		category.setImageURL("CAT_1.png");
		
		assertEquals("Successfully added a category inside the table!",true,categoryDAO.add(category));
		
		category=new Category();
		category.setName("TV");
		category.setDescription("This is Apple Mobile");
		category.setImageURL("CAT_2.png");
		
		assertEquals("Successfully added a category inside the table!",true,categoryDAO.add(category));
		
		//get the single category
		category=categoryDAO.get(2);
		assertEquals("Successfully get a category inside the table!","Mobile",category.getName());
		
		//update the category
		category.setName("Blacberry");
		assertEquals("Successfully updated a category inside the table!",true,categoryDAO.update(category));
		
		//delete the category
		assertEquals("Successfully delete a category inside the table!",true,categoryDAO.delete(category));
		
		//fetching the listof categories
		assertEquals("Successfully fetch the list of categories from the table!",1,categoryDAO.list().size());
	}

}
