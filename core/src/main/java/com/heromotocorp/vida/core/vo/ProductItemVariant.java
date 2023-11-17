package com.heromotocorp.vida.core.vo;

import java.util.ArrayList;
import java.util.List;

	/**
	 * @author shissriv
	 *
	 */
	public class ProductItemVariant {
		
	
		String name;
		String sku;
		String sf_id;
		String __typename;
		
		private List<ProductItemColourVariant> variants= new ArrayList<>();

		@java.lang.SuppressWarnings("squid:S2384")
		public List<ProductItemColourVariant> getVariants() {
			return variants;
		}

		public void setVariants(List<ProductItemColourVariant> variants) {
			this.variants = new ArrayList<>(variants);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSku() {
			return sku;
		}

		public void setSku(String sku) {
			this.sku = sku;
		}

		public String getSf_id() {
			return sf_id;
		}

		public void setSf_id(String sf_id) {
			this.sf_id = sf_id;
		}

		public String get__typename() {
			return __typename;
		}

		public void set__typename(String __typename) {
			this.__typename = __typename;
		}

				
		
		
	}

