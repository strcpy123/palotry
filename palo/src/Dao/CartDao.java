package Dao;

import java.util.List;
import Model.MenuItem;
public interface CartDao {
	public void addCartItem(long userId,long menuItemId);
	public List<MenuItem> getAllCartItems(long userId)throws CartEmptyException;
	public void removeCartItem(long userId,long menuItemId);
}
