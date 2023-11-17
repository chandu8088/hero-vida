/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.wcm.api.Page;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for ProductInfoBeanModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class ProductInfoBeanModelTest {

	private final AemContext context = new AemContext();

	private ProductInfoBeanModel productInfoBeanModel = new ProductInfoBeanModel();

	private List<ProductVariantInfoBeanModdel> productVariantInfoList = new ArrayList<>();

	private Page page;

	private Resource resource;

	private ProductVariantInfoBeanModdel productVariantInfoBeanModdel = new ProductVariantInfoBeanModdel();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		productVariantInfoList.add(productVariantInfoBeanModdel);
		page = context.create().page("/content/testvida");
		resource = context.create().resource(page, "test vida", "sling:resourceType", "vida/components/productinfo",
				"item_name", "item_name", "item_image", "item_image", "varSku", "varSku", "productVariantInfoList",
				productVariantInfoList);

		productInfoBeanModel = resource.adaptTo(ProductInfoBeanModel.class);

	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductInfoBeanModel#getItem_name()}.
	 */
	@Test
	void testGetItem_name() {
		productInfoBeanModel.setItem_name("item_name");
		assertEquals("item_name", productInfoBeanModel.getItem_name());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductInfoBeanModel#setItem_name(java.lang.String)}.
	 */
	@Test
	void testSetItem_name() {
		productInfoBeanModel.setItem_name("item_name");
		assertEquals("item_name", productInfoBeanModel.getItem_name());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductInfoBeanModel#getItem_image()}.
	 */
	@Test
	void testGetItem_image() {
		productInfoBeanModel.setItem_image("item_image");
		assertEquals("item_image", productInfoBeanModel.getItem_image());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductInfoBeanModel#setItem_image(java.lang.String)}.
	 */
	@Test
	void testSetItem_image() {
		productInfoBeanModel.setItem_image("item_image");
		assertEquals("item_image", productInfoBeanModel.getItem_image());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductInfoBeanModel#getProductVariantInfoList()}.
	 */
	@Test
	void testGetProductVariantInfoList() {
		productInfoBeanModel.setProductVariantInfoList(productVariantInfoList);
		assertEquals(productVariantInfoList, productInfoBeanModel.getProductVariantInfoList());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductInfoBeanModel#setProductVariantInfoList(java.util.List)}.
	 */
	@Test
	void testSetProductVariantInfoList() {
		productInfoBeanModel.setProductVariantInfoList(productVariantInfoList);
		assertEquals(productVariantInfoList, productInfoBeanModel.getProductVariantInfoList());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductInfoBeanModel#getVarSku()}.
	 */
	@Test
	void testGetVarSku() {
		productInfoBeanModel.setVarSku("varSku");
		assertEquals("varSku", productInfoBeanModel.getVarSku());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductInfoBeanModel#setVarSku(java.lang.String)}.
	 */
	@Test
	void testSetVarSku() {
		productInfoBeanModel.setVarSku("varSku");
		assertEquals("varSku", productInfoBeanModel.getVarSku());
	}

}
