package Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import Model.Cart;
import Model.MenuItem;

public class CartDaoCollectionImpl implements CartDao {
	private static HashMap<Long, Cart> userCarts;
	private static List<MenuItem> menuItemList;

	public CartDaoCollectionImpl() {
		if (userCarts == null) {
			userCarts = new HashMap<Long, Cart>();
		}
	}

	@Override
	public void addCartItem(long userId, long menuItemId) {
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		MenuItem item = menuItemDao.getMenuItem(menuItemId);
		if (userCarts.containsKey(userId)) {
			Cart cart = userCarts.get(userId);
			menuItemList = cart.getMenuItemList();
			menuItemList.add(item);
			cart.setMenuItemList(menuItemList);
			cart.setTotal(cart.getTotal() + item.getPrice());
			userCarts.put(userId, cart);
		} else {
			menuItemList = new ArrayList<MenuItem>();
			menuItemList.add(item);
			Cart cart = new Cart(menuItemList, item.getPrice());
			userCarts.put(userId, cart);
		}
	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		Cart cart = userCarts.get(new Long(userId));
		List<MenuItem> menuItemList = cart.getMenuItemList();
		if (menuItemList == null || menuItemList.size() == 0) {
			throw new CartEmptyException("Cart is empty");
		}
		double total = 0.0;
		for (MenuItem menuItem : menuItemList) {
			total = total + menuItem.getPrice();

		}
		cart.setTotal(total);
		return menuItemList;
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		Cart cart = userCarts.get(userId);
		ListIterator<MenuItem> iterator = cart.getMenuItemList().listIterator();
		while (iterator.hasNext()) {
			if (iterator.next().getId() == menuItemId) {
				iterator.remove();
			}
		}

	}

}
