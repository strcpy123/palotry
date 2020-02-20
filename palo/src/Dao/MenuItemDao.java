package Dao;

import java.util.List;
import Model.MenuItem;
public interface MenuItemDao {
	public List<MenuItem> getMenuItemListAdmin();
	public List<MenuItem> getMenuItemListCustomer();
	public void  modifyMenuItem(MenuItem menuItem);
	public MenuItem getMenuItem(long menuItemId); 
}
