package com.heromotocorp.vida.core.vo;

import java.util.ArrayList;
import java.util.List;

/** Represent one vida Product 
 * @author shissriv
 *
 */
public class Product {
	
	List<ProductItemVariant> items = new ArrayList<ProductItemVariant>();

	public List<ProductItemVariant> getItems() {
		return items;
	}

	public void setItems(List<ProductItemVariant> items) {
		this.items = items;
	}
	
	
	

}
