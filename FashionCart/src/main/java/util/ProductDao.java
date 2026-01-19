package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashioncart.db.DBConnectionManager;
import com.google.gson.Gson;

public class ProductDao {

	private static final String SELECT_ALL = "SELECT * FROM product";

	private static final String SELECT_BY_CATEGORY = "SELECT * FROM product WHERE category = ?";

	private static final String SELECT_BY_ID = "SELECT * FROM product WHERE product_id = ?";

	public String getAllProducts() {

		List<Product> products = new ArrayList<>();
		String json = null;

		try (Connection con = DBConnectionManager.getInstance().getDataSource().getConnection();
						PreparedStatement ps = con.prepareStatement(SELECT_ALL);
						ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				products.add(mapRow(rs));
			}
			Gson gson = new Gson();
			json = gson.toJson(products);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	public String getProductsByCategory(String category) {

		List<Product> products = new ArrayList<>();
		String json = null;

		try (Connection con = DBConnectionManager.getInstance().getDataSource().getConnection();
						PreparedStatement ps = con.prepareStatement(SELECT_BY_CATEGORY)) {

			ps.setString(1, category);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					products.add(mapRow(rs));
				}
			}
			Gson gson = new Gson();
			json = gson.toJson(products);

			;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	public String getProductById(int productId) {

		Product product = null;
		String json = null;

		try (Connection con = DBConnectionManager.getInstance().getDataSource().getConnection();
						PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {

			ps.setInt(1, productId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					product = mapRow(rs);
				}
			}
			Gson gson = new Gson();
			json = gson.toJson(product);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}

	private Product mapRow(ResultSet rs) throws Exception {

		Product p = new Product();
		p.setProductId(rs.getInt("product_id"));
		p.setProductName(rs.getString("product_name"));
		p.setCategory(rs.getString("category"));
		p.setPrice(rs.getBigDecimal("price"));
		p.setImagePath(rs.getString("image_path"));
		p.setAvailability(rs.getString("availability"));
		p.setDescription(rs.getString("description"));

		return p;
	}
}
