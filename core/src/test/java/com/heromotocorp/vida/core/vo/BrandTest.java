package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for Brand
 */
@ExtendWith(AemContextExtension.class)
class BrandTest {

	private Brand brand = new Brand();

	List<ExchangeVehicleVO> brandCategory = new ArrayList<>();

	@Test
	void testGetBrandName() {
		brand.setBrandName("brand");

		brand.getBrandName();

		assertEquals("brand", brand.getBrandName());
	}

	@Test
	void testSetBrandName() {
		brand.setBrandName("brand");

		assertEquals("brand", brand.getBrandName());
	}

	@Test
	void testGetBrandCategory() {
		brand.setBrandCategory(brandCategory);

		brand.getBrandCategory();

		assertEquals(brandCategory, brand.getBrandCategory());
	}

	@Test
	void testSetBrandCategory() {
		brand.setBrandCategory(brandCategory);

		assertEquals(brandCategory, brand.getBrandCategory());
	}

	@Test
	void testGetBrandValue() {
		brand.setBrandValue("value");

		brand.getBrandValue();

		assertEquals("value", brand.getBrandValue());
	}

	@Test
	void testSetBrandValue() {
		brand.setBrandValue("value");

		assertEquals("value", brand.getBrandValue());
	}

}
