package com.fashioncart.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.ProductDao;

public class ListProductsCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		ProductDao product = new ProductDao();
		String json = product.getAllProducts();
		request.setAttribute("product array", json);
		if (json != null) {
			return true;
		}
		return false;
	}
}
