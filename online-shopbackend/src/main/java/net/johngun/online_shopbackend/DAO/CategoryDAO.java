package net.johngun.online_shopbackend.DAO;

import java.util.List;

import net.johngun.online_shopbackend.dto.Category;

public interface CategoryDAO {

	List<Category> list();
	Category getCategory(int id);
}
