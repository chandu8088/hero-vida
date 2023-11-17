/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
 * JUnit test class for StaticProductSpecModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class StaticProductSpecModelTest {

	private final AemContext context = new AemContext();

	private StaticProductSpecModel staticProductSpecModel = new StaticProductSpecModel();

	private StaticProductSpecModel staticProductSpecModel1 = new StaticProductSpecModel();

	private Page page;

	private Resource resource;

	private Page page1;

	private Resource resource1;

	private List<StaticProductSpecBeanModel> staticspecifications = new ArrayList<>();

	private StaticProductSpecBeanModel staticProductSpecBeanModel = new StaticProductSpecBeanModel();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		staticspecifications.add(staticProductSpecBeanModel);
		page = context.create().page("/content/testvida");
		resource = context.create().resource(page, "test vida", "sling:resourceType", "vida/components/productcompare",
				"staticspecifications", staticspecifications);

		staticProductSpecModel = resource.adaptTo(StaticProductSpecModel.class);

		page1 = context.create().page("/content/testvida");
		resource1 = context.create().resource(page1, "test vida", "sling:resourceType",
				"vida/components/productcompare", "staticspecifications", null);

		staticProductSpecModel1 = resource1.adaptTo(StaticProductSpecModel.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.StaticProductSpecModel#getItems()}.
	 */
	@Test
	void testGetItems() {
		staticProductSpecModel.getItems();
		assertNotNull(staticProductSpecModel);

		staticProductSpecModel1.getItems();
	}

}
