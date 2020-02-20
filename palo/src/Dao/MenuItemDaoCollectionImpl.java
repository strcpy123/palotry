package Dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import Model.MenuItem;

public class MenuItemDaoCollectionImpl implements MenuItemDao {
	private static List<MenuItem> menuItemList;
	MenuItem menuItem;

	public MenuItemDaoCollectionImpl() {
		super();
	}

	public MenuItemDaoCollectionImpl(long id, String name, float price, boolean active, Date dateOfLaunch,
			String category, boolean freeDelivery) {
		super();

		menuItem = new MenuItem(id, name, price, active, dateOfLaunch, category, freeDelivery);
		if (menuItemList == null) {
			menuItemList = new ArrayList<MenuItem>();
			menuItem.setId(id);
			menuItem.setName(name);
			menuItem.setPrice(price);
			menuItem.setActive(active);
			menuItem.setDateOfLaunch(dateOfLaunch);
			menuItem.setCategory(category);
			menuItem.setFreeDelivery(freeDelivery);
			menuItemList.add(menuItem);
		}
	}

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		Date date1 = new Date();
		List<MenuItem> menuListCustomer = new ArrayList<MenuItem>();
		for (int i = 0; i < menuItemList.size(); i++) {
			if ((menuItemList.get(i).getDateOfLaunch().getTime() <= date1.getTime())
					&& menuItemList.get(i).isActive()) {
				menuListCustomer.add(menuItemList.get(i));
			}
		}
		return menuListCustomer;
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		@SuppressWarnings("rawtypes")
		Iterator iter = menuItemList.iterator();
		while (iter.hasNext()) {
			if (iter.equals(menuItem)) {
				menuItemList.set(menuItemList.indexOf(menuItem), menuItem);
			}
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public MenuItem getMenuItem(long menuItemId) {
		@SuppressWarnings("rawtypes")
		Iterator iter = menuItemList.iterator();
		while (iter.hasNext()) {
			if (iter.equals(menuItemId)) {
				return menuItem;
			}
		}
		return null;

	}

}
