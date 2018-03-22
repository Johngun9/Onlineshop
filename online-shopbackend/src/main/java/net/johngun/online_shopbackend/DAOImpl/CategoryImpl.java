package net.johngun.online_shopbackend.DAOImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.johngun.online_shopbackend.DAO.CategoryDAO;
import net.johngun.online_shopbackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryImpl implements CategoryDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	
	@Override
	public List<Category> list() {
		
		String selectActiveCategory= "FROM Category WHERE active=:active";
		
		Query query=sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		
		query.setParameter("active", true);

		return query.getResultList();
	}
	
	
	 //Getting the Single Categroy 
	@Override
	public Category get(int id) {
		
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}
	
	 //Adding the categroy	 
	@Override
	public boolean add(Category category) {
		try{
			//add category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		
	}
	
	// update the single categroy	
	@Override
	public boolean update(Category category) {
		try{
			sessionFactory.getCurrentSession().update(category);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		
	}
	
	// deleting category(just make its flag as false
	@Override
	public boolean delete(Category category) {
		
		category.setActive(false);
		
		try{
			sessionFactory.getCurrentSession().update(category);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

}
