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

import com.heromotocorp.vida.core.vo.HotspotBeanModel;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for HotspotModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class HotspotModelTest {

	private final AemContext context = new AemContext();

	private HotspotModel hotspotModel = new HotspotModel();

	private HotspotBeanModel hotspotBeanModel = new HotspotBeanModel();

	private Page page;

	private Resource resource;

	private List<HotspotBeanModel> items = new ArrayList<>();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		items.add(hotspotBeanModel);
		page = context.create().page("/content/samplevida");
		resource = context.create().resource(page, "sample vida", "sling:resourceType", "vida/components/hotspot",
				"items", items);

		hotspotModel = resource.adaptTo(HotspotModel.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.HotspotModel#getItems()}.
	 */
	@Test
	void testGetItems() {
		hotspotModel.getItems();
		assertNotNull(items);

	}

}
