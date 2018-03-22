package net.johngun.online_shopbackend.DAO;

import java.util.List;

import net.johngun.online_shopbackend.dto.Category;

public interface CategoryDAO {
	
	Category get(int id);
	List<Category> list();
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
}
