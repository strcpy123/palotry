package Dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

//pending
import java.util.List;

import Model.MenuItem;
import Util.DateUtil;

public class MenuItemDaoCollectionImplTest {
	static Scanner in = new Scanner(System.in);

	public static void testGetMenuItemListAdmin() {
		System.out.println("Enter Your Id");
		long id = in.nextLong();
		System.out.println("Enter Your Name");
		String name = in.nextLine();
		System.out.println("Enter Your Price");
		float price = in.nextFloat();
		System.out.println("Enter mode of Active 'true'/'false'");
		boolean active = in.nextBoolean();
		System.out.println("Enter date of Launch");
		String date = in.nextLine();
		Date dateObj = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD/MM/YYYY");
		try {
			dateObj = simpleDateFormat.parse(date);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Enter Your Category");
		String category = in.nextLine();
		System.out.println("Enter Delivery Mode 'true'/'false'");
		boolean freeDelivery = in.nextBoolean();
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl(id, name, price, active, dateObj, category,
				freeDelivery);
		List<MenuItem> menuList = menuItemDao.getMenuItemListAdmin();
		for (int i = 0; i < menuList.size(); i++) {
			System.out.println(menuList.get(i));
		}
	}

	public static void testGetMenuItemListCustomer() {
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		List<MenuItem> menuList = menuItemDao.getMenuItemListCustomer();
		for (int i = 0; i < menuList.size(); i++) {
			System.out.println(menuList.get(i));
		}
	}

	public static void testModifyMenuItem() {
		MenuItem menuItem = null;
		MenuItemDao menuItemDao = null;
		ArrayList<MenuItem> al = (ArrayList<MenuItem>) new MenuItemDaoCollectionImpl().getMenuItemListAdmin();
		int i = 0;
		spidy: while (i == 0) {
			System.out.println("Enter Your Id");
			long id = in.nextLong();
			System.out.println("Enter Your Name");
			String name = in.nextLine();
			System.out.println("Enter Your Price");
			float price = in.nextFloat();
			System.out.println("Enter mode of Active 'true'/'false'");
			boolean active = in.nextBoolean();
			System.out.println("Enter date of Launch");
			String date = in.nextLine();
			Date dateObj = DateUtil.convertToDate(date);
			System.out.println("Enter Your Category");
			String category = in.nextLine();
			System.out.println("Enter Delivery Mode 'true'/'false'");
			boolean freeDelivery = in.nextBoolean();
			menuItem = new MenuItem(id, name, price, active, dateObj, category, freeDelivery);
			int j = 0;
			for (j = 0; j < al.size(); j++) {
				if (al.get(j).getId() == menuItem.getId()) {
					menuItemDao = new MenuItemDaoCollectionImpl();
					menuItemDao.modifyMenuItem(menuItem);
					System.out.println("After modification" + menuItemDao.getMenuItem(id));
					i++;
					break;
				}

			}
			if (j == al.size()) {
				continue spidy;
			}

		}
	}
	public static void testGetMenuItem() {
	}

	public static void main(String[] args) {
		testGetMenuItemListAdmin();
		testGetMenuItemListCustomer();
		testModifyMenuItem();
		in.close();

	}

}
