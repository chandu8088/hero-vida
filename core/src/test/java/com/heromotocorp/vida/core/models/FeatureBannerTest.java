/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.wcm.api.Page;


import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for FeatureBanner
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class FeatureBannerTest {

	private final AemContext context = new AemContext();

	private FeatureBanner featureBanner = new FeatureBanner();

	private Page page;

	private Resource resource;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/samplevida");
		resource = context.create().resource(page, "sample vida", "sling:resourceType", "vida/components/featurebanner",
				"featurebanner", null, "variations", "variations");

		featureBanner = resource.adaptTo(FeatureBanner.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.FeatureBanner#getVariations()}.
	 */
	@Test
	void testGetVariations() {
		featureBanner.getVariations();
		assertEquals("variations", featureBanner.getVariations());
	}

}
