package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.MenuItem;

public class MenuItemDaoSqlImpl implements MenuItemDao {
	PreparedStatement preparedStatement=null;
	MenuItem menuItem=null;
	@SuppressWarnings("rawtypes")
	private List menuItemList;
	@SuppressWarnings("unchecked")
	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		try {
			String query ="select * from menu_item";
			preparedStatement=ConnectionHandler.getConnection().prepareStatement(query);
			menuItemList = new ArrayList<MenuItem>();
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){  
				menuItemList.add(resultSet.getLong(1));
				menuItemList.add(resultSet.getString(2));
				menuItemList.add(resultSet.getFloat(3));
				menuItemList.add(resultSet.getBoolean(4));
				menuItemList.add(resultSet.getDate(5));
				menuItemList.add(resultSet.getString(6));
				menuItemList.add(resultSet.getBoolean(7));
				}  
		}
		catch(SQLException |ClassNotFoundException e) {
			e.printStackTrace();
		}
		return menuItemList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		// TODO Auto-generated method stub
		try {
			String query ="select * from menu_item where Active='Yes' AND DateOfLaunch >=CURDATE() ";
			preparedStatement=ConnectionHandler.getConnection().prepareStatement(query);
			menuItemList = new ArrayList<MenuItem>();
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){  
				menuItemList.add(resultSet.getLong(1));
				menuItemList.add(resultSet.getString(2));
				menuItemList.add(resultSet.getFloat(3));
				menuItemList.add(resultSet.getBoolean(4));
				menuItemList.add(resultSet.getDate(5));
				menuItemList.add(resultSet.getString(6));
				menuItemList.add(resultSet.getBoolean(7));
				}  
		}
		catch(SQLException |ClassNotFoundException e) {
			e.printStackTrace();
		}
		return menuItemList;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		String query="Select * from menu_item groupby id";
		try {
			preparedStatement=ConnectionHandler.getConnection().prepareStatement(query);
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		// TODO Auto-generated method stub
		menuItem = new MenuItem();
		
		try {
			String query="Select * from menu_item groupby id";
			preparedStatement=ConnectionHandler.getConnection().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){  
				menuItem.setId(resultSet.getLong(1));
				menuItem.setName(resultSet.getString(2));
				menuItem.setPrice(resultSet.getFloat(3));
				menuItem.setActive(resultSet.getBoolean(4));
				menuItem.setDateOfLaunch(resultSet.getDate(5));
				menuItem.setCategory(resultSet.getString(6));
				menuItem.setFreeDelivery(resultSet.getBoolean(7));
				}  
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuItem;
	}

}
