package Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Cart;
import Model.MenuItem;

public class CartDaoSqlImpl implements CartDao {
	@Override
	public void addCartItem(long userId, long menuItemId) {
		String query = "INSERT INTO CART VALUES(?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = ConnectionHandler.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, (int) userId);
			preparedStatement.setInt(2, (int) menuItemId);
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		Cart cart = new Cart(menuItemList, 0);
		try {
			PreparedStatement preparedStatement = ConnectionHandler.getConnection().prepareStatement(
					"select menu_items.id, menu_items.Names, menu_items.Price, menu_items.Active, menu_items.DateOfLaunch, menu_items.Category, menu_items.FreeDelivery from menu_items inner join Cart on menu_items.id = Cart.menu_id where cart.customer_ids = ?");
			preparedStatement.setInt(1, (int) userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MenuItem menuItem = new MenuItem(resultSet.getInt("id"), resultSet.getString("Names"),
						resultSet.getFloat("price"), resultSet.getString("active").equals("Yes") ? true : false,
						resultSet.getDate("DateOfLaunch"), resultSet.getString("category"),
						resultSet.getString("FreeDelivery").equals("Yes") ? true : false);
			menuItemList.add(menuItem);
			}
			cart.setMenuItemList(menuItemList);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (menuItemList.isEmpty()) {
			throw new CartEmptyException("Cart is Empty");
		}
		try {
			PreparedStatement preparedStatement = ConnectionHandler.getConnection().prepareStatement(
					"select sum(menu_items.Price) from menu_items inner join cart on menu_item.id = cart.menu_id where cart.customer_id = ?");
			preparedStatement.setInt(1, (int) userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				cart.setTotal(resultSet.getDouble(1));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		try {
			PreparedStatement statement = ConnectionHandler.getConnection().prepareStatement("delete from cart where customer_id = ? and menu_id = ?");
			statement.setInt(1, (int)userId);
			statement.setInt(2, (int)menuItemId);
			statement.executeUpdate();
			System.out.println("Item Removed Successfully");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
		CartDaoSqlImpl cartDaoSqlImpl = new CartDaoSqlImpl();
		cartDaoSqlImpl.addCartItem(1, 1);
		try {
			System.out.println(cartDaoSqlImpl.getAllCartItems(1));
		} catch (CartEmptyException e) {
			e.printStackTrace();
		}
		cartDaoSqlImpl.removeCartItem(1, 1);
	}

}
